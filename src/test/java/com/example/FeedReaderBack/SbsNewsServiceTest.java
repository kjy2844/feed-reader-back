package com.example.FeedReaderBack;

import com.example.FeedReaderBack.model.BroadcastStationItem;
import com.example.FeedReaderBack.model.SbsNewsItem;
import com.example.FeedReaderBack.repo.SbsNewsRepo;
import com.example.FeedReaderBack.service.SbsNewsService;
import com.example.FeedReaderBack.service.SbsNewsServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class SbsNewsServiceTest {
    @Mock
    private SbsNewsRepo sbsNewsRepo;

    @InjectMocks
    private SbsNewsServiceImpl sbsNewsServiceImpl;

    private List<SbsNewsItem> sbsNews = new ArrayList<>();

    @Test
    public void mockFindNext10Test() {
        SbsNewsItem sbsNewsItem = new SbsNewsItem("title", "link", "author", "content", "image", Instant.now(), new BroadcastStationItem());
        sbsNews.add(sbsNewsItem);

        given(sbsNewsRepo.findTop10ByOrderByUpdatedDesc()).willReturn(sbsNews);

        Iterable<SbsNewsItem> findSbsNewsItems = sbsNewsServiceImpl.findAll();

        Assertions.assertEquals(sbsNews, Lists.newArrayList(findSbsNewsItems));
    }
}
