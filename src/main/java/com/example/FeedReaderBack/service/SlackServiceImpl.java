package com.example.FeedReaderBack.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.UserTypingEvent;
import com.slack.api.rtm.RTMEventHandler;
import com.slack.api.rtm.RTMEventsDispatcher;
import com.slack.api.rtm.RTMEventsDispatcherFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackServiceImpl implements SlackService {
    @Override
    public void postSlack(String message) throws IOException, SlackApiException {
        Slack slack = Slack.getInstance();
        // Load an env variable
        // If the token is a bot token, it starts with `xoxb-` while if it's a user token, it starts with `xoxp-`
        //String token = System.getenv("SLACK_TOKEN");

        // Initialize an API Methods client with the given token

        MethodsClient methods = slack.methods("xoxb-1302025478628-1289230553734-hj81XzS5JjbYuLhKruEFBooq");

        // Build a request object
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("C018Q50JAJX") // Use a channel ID `C1234567` is preferrable
                .text(message)
                .build();

        // Get a response as a Java object
        ChatPostMessageResponse response = methods.chatPostMessage(request);
    }

}
