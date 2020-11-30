package com.subd.dao;

import com.subd.model.Line;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends MongoRepository<Line, Long> {
}
