package com.subd.dao;

import com.subd.model.DbTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends MongoRepository<DbTable, Long> {
}
