package com.subd.dao;

import com.subd.model.Attribute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends MongoRepository<Attribute, Long> {
}
