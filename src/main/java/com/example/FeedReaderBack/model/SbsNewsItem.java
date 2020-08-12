package com.example.FeedReaderBack.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class SbsNewsItem {

//    private String id;
    private String title;
    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private String link;
    private String author;
    @Column(columnDefinition = "LONGTEXT")
    // blob이나 text는 indexing이 불가능하거나 안될 수 있음!
    // pointer처럼 작동
    private String content;
    private String image;
    private Instant updated;
}
