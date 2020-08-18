package com.example.FeedReaderBack.repo;

import com.example.FeedReaderBack.model.BroadcastStationItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastStationRepo extends CrudRepository<BroadcastStationItem, Long> {
}
