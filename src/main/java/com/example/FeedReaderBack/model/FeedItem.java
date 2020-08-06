package com.example.FeedReaderBack.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
// @Table(name = "feeds")
@Getter
@Setter
public class FeedItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private String metadata;
    private String summary;
    private String visual;
}
