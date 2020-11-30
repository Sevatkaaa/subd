package com.subd.dao;

import com.subd.model.Header;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends MongoRepository<Header, Long> {
}
