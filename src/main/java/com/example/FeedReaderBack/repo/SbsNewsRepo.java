package com.example.FeedReaderBack.repo;

import com.example.FeedReaderBack.model.SbsNewsItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SbsNewsRepo extends CrudRepository<SbsNewsItem, String> {
    public List<SbsNewsItem> findByLink(String link);
    public List<SbsNewsItem> findTop10ByOrderByUpdatedDesc();
    public List<SbsNewsItem> findTop10ByUpdatedBeforeOrderByUpdatedDesc(Instant updated);
}