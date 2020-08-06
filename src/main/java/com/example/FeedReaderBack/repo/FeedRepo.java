package com.example.FeedReaderBack.repo;

import com.example.FeedReaderBack.model.FeedItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepo extends CrudRepository<FeedItem, Integer> {
}
