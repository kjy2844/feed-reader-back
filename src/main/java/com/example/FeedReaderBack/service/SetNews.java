package com.example.FeedReaderBack.service;

import com.example.FeedReaderBack.model.BroadcastStationItem;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.repo.BroadcastStationRepo;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.Instant;

@Service
public class SetNews {

    private static String getTagValue(String tag, Element eElement){
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public SbsNewsItem getSbsNewsItem(Element eElement, BroadcastStationItem sbs) {
        SbsNewsItem sbsNewsItem = new SbsNewsItem();
        sbsNewsItem.setTitle(getTagValue("title", eElement));
        sbsNewsItem.setLink(getTagValue("link", eElement));
        sbsNewsItem.setAuthor(getTagValue("author", eElement));
        sbsNewsItem.setBroadcastStationItem(sbs);


        org.jsoup.nodes.Document doc = Jsoup.parse(getTagValue("content:encoded", eElement));
        Elements ps = doc.select("p.change");

        sbsNewsItem.setContent(ps.text());

        if(eElement.getElementsByTagName("enclosure").getLength() == 1){
            Element enc = (Element) eElement.getElementsByTagName("enclosure").item(0);
            sbsNewsItem.setImage(enc.getAttribute("url"));
        }

        sbsNewsItem.setUpdated(Instant.now()); //clock -> 가짜 테스트 좋다
        return sbsNewsItem;
    }
}
