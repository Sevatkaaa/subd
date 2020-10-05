package com.subd.dao;

import com.subd.model.DbTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<DbTable, Long> {
}
