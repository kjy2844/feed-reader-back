package com.example.FeedReaderBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BroadcastStationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private Long id;

    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private String name;

    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private String homelink;

    @OneToMany
    @JoinColumn(name = "broadcast_station_item_id")
    @JsonView({ApiJsonView.BroadcastStation.class})
    // jsonignore
    // dto
    // jackson 설정
    private List<SbsNewsItem> sbsNewsItem;
    // Query DSL
    // 그래프큐엘 -> 웹 api연동에 사용, http
}
