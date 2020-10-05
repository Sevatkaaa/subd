package com.subd.service;

import com.subd.dao.TableRepository;
import com.subd.model.Database;
import com.subd.model.DbTable;
import com.subd.model.Header;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TableService {

    @Resource
    private TableRepository tableRepository;

    @Resource
    private DatabaseService databaseService;

    public DbTable getTable(Long id) {
        return tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table does not exist"));
    }

    public List<DbTable> getTables() {
        return tableRepository.findAll();
    }

    public DbTable createTable(String name, Long dbId) {
        Database database = databaseService.getDatabase(dbId);
        DbTable table = new DbTable();
        table.setDtbs(database);
        table.setName(name);
        table.setHeader(new Header());
        return tableRepository.save(table);
    }
}
