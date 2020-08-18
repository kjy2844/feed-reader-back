package com.example.FeedReaderBack.model;

import lombok.Data;

@Data
public class SlackEvent {
    @Data
    public class SlackEventMsg{
        private String client_msg_id;
        private String channel;
        private String text;
    }

    private String token;
    private SlackEventMsg event;
    private String challenge;
}
