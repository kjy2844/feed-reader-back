package com.example.FeedReaderBack.controller;

import com.example.FeedReaderBack.model.ApiJsonView;
import com.example.FeedReaderBack.model.BroadcastStationItem;
import com.example.FeedReaderBack.model.FeedItem;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.repo.BroadcastStationRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Controller
@RequestMapping
@CrossOrigin
public class StationController {
    private final BroadcastStationRepo broadcastStationRepo;
    public StationController(BroadcastStationRepo broadcastStationRepo) {
        this.broadcastStationRepo = broadcastStationRepo;
    }

    @GetMapping(value = "/addStation")
    public @ResponseBody Iterable<BroadcastStationItem> addStation() {
        BroadcastStationItem broadcastStationItem = new BroadcastStationItem();
        broadcastStationItem.setName("SBS");
        broadcastStationItem.setHomelink("https://news.sbs.co.kr/news/newsMain.do");
        broadcastStationRepo.save(broadcastStationItem);
        return broadcastStationRepo.findAll();
    }

    @GetMapping(value = "/broadcaststation")
    @JsonView(ApiJsonView.BroadcastStation.class)
    public @ResponseBody
    Iterable<BroadcastStationItem> getAllBroadcastStation() {
        return broadcastStationRepo.findAll();
    }
}
