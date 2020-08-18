package com.example.FeedReaderBack.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.time.Instant;

@Entity
@Getter @Setter
@AllArgsConstructor
public class SbsNewsItem {

//    private String id;
    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private String title;

    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private String link;

    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private String author;

    @Column(columnDefinition = "LONGTEXT")
    // blob이나 text는 indexing이 불가능하거나 안될 수 있음!
    // pointer처럼 작동
    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private String content;

    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private String image;

    @JsonView({ApiJsonView.SbsNews.class, ApiJsonView.BroadcastStation.class})
    private Instant updated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonView({ApiJsonView.SbsNews.class})
    private BroadcastStationItem broadcastStationItem;

    // 저장 프로시저
    // 오라클 쿼리 힌트 -> 코스트 기반 쿼리 실행의 문제점 보완 가능
    // 마이에스큐엘 조인 루프 문제

   public SbsNewsItem() {
    }

}
