package com.subd.dao;

import com.subd.model.Database;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends MongoRepository<Database, Long> {
}
