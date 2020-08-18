package com.example.FeedReaderBack.service;

import com.example.FeedReaderBack.model.BroadcastStationItem;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.repo.BroadcastStationRepo;
import com.example.FeedReaderBack.repo.SbsNewsRepo;
import com.slack.api.methods.SlackApiException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
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
    private final SlackService slackService;
    private final BroadcastStationRepo broadcastStationRepo;
    private final SetNews setNews;
    public SbsNewsServiceImpl(SbsNewsRepo sbsNewsRepo, SlackService slackService, BroadcastStationRepo broadcastStationRepo, SetNews setNews) {
        this.sbsNewsRepo = sbsNewsRepo;
        this.slackService = slackService;
        this.broadcastStationRepo = broadcastStationRepo;
        this.setNews = setNews;
    }

    @Override
    @Scheduled(cron = "0 0/5 * * * *")
    public ResponseEntity<String> getRss() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, SlackApiException {
        String url = "https://news.sbs.co.kr/news/SectionRssFeed.do?sectionId=01&plink=RSSREADER";

        ResponseEntity<String> result = getStringResponseEntity(url);

        Document document = getDocument(result);

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

//                if (!sbsNewsRepo.findByLink(getTagValue("link", eElement)).isPresent()) {
//                    slackService.postSlack(getTagValue("link", eElement));
//                }
                BroadcastStationItem sbs = broadcastStationRepo.findAll().iterator().next();

                SbsNewsItem sbsNewsItem = setNews.getSbsNewsItem(eElement, sbs);
                // 시간 문제가 생기는 것을 해결하려면
                // 1. sleep
                // 2. 항상 증가하는 시계 instant의 무언가 -> 내부적 슬립 가능성 존재 or 서서히 시계를 맞추는 리눅스 같ㅇㄴ 그런애

                System.out.println(sbsNewsItem.getContent().length());
                sbsNewsRepo.save(sbsNewsItem);

            }
        }

        return result;
    }

    private Document getDocument(ResponseEntity<String> result) throws ParserConfigurationException, SAXException, IOException {
        InputSource is = new InputSource(new StringReader(result.getBody()));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(is);
    }

    private ResponseEntity<String> getStringResponseEntity(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
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
