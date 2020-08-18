package com.example.FeedReaderBack.service;

import com.slack.api.methods.SlackApiException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface SlackService {
    void postSlack(String message) throws IOException, SlackApiException;
}
