package com.subd.dao;

import com.subd.model.LineObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineObjectRepository extends JpaRepository<LineObject, Long> {
}
