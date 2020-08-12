package com.example.FeedReaderBack.service;

import com.example.FeedReaderBack.model.SbsNewsItem;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.time.Instant;

public interface SbsNewsService {
    ResponseEntity<String> getRss() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException;
    Iterable<SbsNewsItem> findAll();
    Iterable<SbsNewsItem> findNext10(Instant updated);
}
