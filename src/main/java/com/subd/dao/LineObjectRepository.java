package com.subd.dao;

import com.subd.model.LineObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineObjectRepository extends MongoRepository<LineObject, Long> {
    List<LineObject> findByName(String name);

    List<LineObject> findByValue(String value);
}
