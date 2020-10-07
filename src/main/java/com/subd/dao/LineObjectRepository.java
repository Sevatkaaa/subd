package com.subd.dao;

import com.subd.model.LineObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineObjectRepository extends JpaRepository<LineObject, Long> {
    List<LineObject> findByName(String name);

    List<LineObject> findByValue(String value);
}
