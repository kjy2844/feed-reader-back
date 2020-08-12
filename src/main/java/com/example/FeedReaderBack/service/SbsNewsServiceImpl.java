package com.example.FeedReaderBack.service;

import com.example.FeedReaderBack.model.FeedItem;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.repo.SbsNewsRepo;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;

@Service
@EnableScheduling
public class SbsNewsServiceImpl implements SbsNewsService {

    private final SbsNewsRepo sbsNewsRepo;
    public SbsNewsServiceImpl(SbsNewsRepo sbsNewsRepo) {
        this.sbsNewsRepo = sbsNewsRepo;
    }

    private static String getTagValue(String tag, Element eElement){
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    @Override
    @Scheduled(cron = "0 0/5 * * * *")
    public ResponseEntity<String> getRss() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        String url = "https://news.sbs.co.kr/news/SectionRssFeed.do?sectionId=01&plink=RSSREADER";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        InputSource is = new InputSource(new StringReader(result.getBody()));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

//        XPathFactory xPathFactory = XPathFactory.newInstance();
//        XPath xpath = xPathFactory.newXPath();
//
//        XPathExpression expr = xpath.compile("//item");
//
//        Object eval = expr.evaluate(document, XPathConstants.NODESET);
//
//        NodeList nodes = (NodeList)eval;
//        for(int i = 0; i < nodes.getLength(); i++){
//            Element element = (Element)nodes.item(i);
//            System.out.println(element.getNodeName() + i);
//        }

        NodeList nList = document.getElementsByTagName("item");

        for(int temp = nList.getLength() - 1; temp >= 0; temp--){
            Node nNode = nList.item(temp);
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                SbsNewsItem sbsNewsItem = new SbsNewsItem();

                sbsNewsItem.setTitle(getTagValue("title", eElement));
                sbsNewsItem.setLink(getTagValue("link", eElement));
                sbsNewsItem.setAuthor(getTagValue("author", eElement));


                org.jsoup.nodes.Document doc = Jsoup.parse(getTagValue("content:encoded", eElement));
                Elements ps = doc.select("p.change");

                sbsNewsItem.setContent(ps.text());

                if(eElement.getElementsByTagName("enclosure").getLength() == 1){
                    Element enc = (Element) eElement.getElementsByTagName("enclosure").item(0);
                    sbsNewsItem.setImage(enc.getAttribute("url"));
                }

                sbsNewsItem.setUpdated(Instant.now()); //clock -> 가짜 테스트 좋다
                // 시간 문제가 생기는 것을 해결하려면
                // 1. sleep
                // 2. 항상 증가하는 시계 instant의 무언가 -> 내부적 슬립 가능성 존재 or 서서히 시계를 맞추는 리눅스 같ㅇㄴ 그런애

                System.out.println(sbsNewsItem.getContent().length());
                sbsNewsRepo.save(sbsNewsItem);

            }
        }

        return result;
    }

    @Override
    public Iterable<SbsNewsItem> findAll() {
        return sbsNewsRepo.findTop10ByOrderByUpdatedDesc();
    }

    @Override
    public Iterable<SbsNewsItem> findNext10(Instant updated) {
        return sbsNewsRepo.findTop10ByUpdatedBeforeOrderByUpdatedDesc(updated);
    }
}
