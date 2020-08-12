package com.example.FeedReaderBack.controller;

import com.example.FeedReaderBack.model.FeedItem;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.service.SbsNewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.time.Instant;

@Controller
@RequestMapping
@CrossOrigin
public class RssController {
    private final SbsNewsService sbsNewsService;
    public RssController(SbsNewsService sbsNewsService) {
        this.sbsNewsService = sbsNewsService;
    }

    @GetMapping(value="/getNews")
    public ResponseEntity<String> getXml () throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        return sbsNewsService.getRss();
    }

    @GetMapping(value = "/sbsNews")
    public @ResponseBody
    Iterable<SbsNewsItem> getAllSbsNews(@RequestParam(required = false) Instant lastdate) {
        if (lastdate != null) {
            return sbsNewsService.findNext10(lastdate);
        }
        return sbsNewsService.findAll();
    }
}
