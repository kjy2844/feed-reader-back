package com.example.FeedReaderBack.controller;

import com.example.FeedReaderBack.model.FeedItem;
import com.example.FeedReaderBack.repo.FeedRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@CrossOrigin
public class MainController {

    private final FeedRepo feedRepo;
    public MainController(FeedRepo feedRepo) {
        this.feedRepo = feedRepo;
    }

    @PostMapping(value="/add")
    public @ResponseBody FeedItem addNewFeed (@RequestBody FeedItem feedItem){
        feedRepo.save(feedItem);
        return feedItem;
    }

    @GetMapping(value="/all")
    public @ResponseBody Iterable<FeedItem> getAllFeeds() {
        return feedRepo.findAll();
    }
}
