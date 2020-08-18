package com.example.FeedReaderBack.controller;

import com.example.FeedReaderBack.model.ApiJsonView;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.model.SlackEvent;
import com.example.FeedReaderBack.service.SbsNewsService;
import com.example.FeedReaderBack.service.SlackService;
import com.fasterxml.jackson.annotation.JsonView;
import com.slack.api.methods.SlackApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;

@Controller
@RequestMapping
@CrossOrigin
public class RssController {
    private final SbsNewsService sbsNewsService;
    private final SlackService slackService;
    public RssController(SbsNewsService sbsNewsService, SlackService slackService) {
        this.sbsNewsService = sbsNewsService;
        this.slackService = slackService;
    }

    @GetMapping(value="/getNews")
    public ResponseEntity<String> getXml () throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, SlackApiException {
        return sbsNewsService.getRss();
    }

    @GetMapping(value = "/sbsNews")
    @JsonView(ApiJsonView.SbsNews.class)
    public @ResponseBody
    Iterable<SbsNewsItem> getAllSbsNews(@RequestParam(required = false) Instant lastdate) {
        if (lastdate != null) {
            return sbsNewsService.findNext10(lastdate);
        }
        return sbsNewsService.findAll();
    }



    @GetMapping(value = "/slacktest")
    public ResponseEntity<String> postSlack() throws IOException, SlackApiException {
        slackService.postSlack("new message");
        return ResponseEntity.ok("success");
        // link로 find했을 때 없다면 무조건 보내줌
        // 만약 내용이 바뀌었다면 어떻게 할 지 생각해야함
        // sbs가 시간 순서대로 보내준다면 중간에 멈춰도 됨
        // 보여주는 방식이 슬랙 자유
        // 슬랙에서 푸시 받을 수도 있음 -> 사용자 말에 대한 대답 가능
        // 슬랙에서 특정 사건에 대해 알아서 서버로 요청 보내줌
        // 기능이 있는 메시지도 만들 수 있음 ex 버튼
    }

    @PostMapping(value = "/typeSbs")
    public ResponseEntity<String> typeSbs(@RequestBody SlackEvent eventapi) throws IOException, SlackApiException {
        if (!"ioqTeEbE6wdl7GVFxmWt6F8s".equals(eventapi.getToken())) return new ResponseEntity<String>("no!", HttpStatus.BAD_REQUEST);
        String challenge = eventapi.getChallenge();
        if (challenge!=null) {
            return new ResponseEntity<String> (challenge, HttpStatus.OK);
        }
        if(eventapi.getEvent().getChannel().equals("C018Q50JAJX") && "news".equals(eventapi.getEvent().getText())){
            slackService.postSlack("message");
        }
        return new ResponseEntity<String> ("news", HttpStatus.OK);
        // 채널, 내용 검사
    }
}
