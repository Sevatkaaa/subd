package com.subd.service;

import com.subd.dao.TableRepository;
import com.subd.model.Database;
import com.subd.model.DbTable;
import com.subd.model.Header;
import com.subd.model.Line;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TableService {

    @Resource
    private TableRepository tableRepository;

    @Resource
    private DatabaseService databaseService;

    @Resource
    private LineService lineService;

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

    public void deleteTable(Long tableId) {
        tableRepository.deleteById(tableId);
    }

    public void deleteTableDuplicates(Long tableId) {
        DbTable table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException("Table does not exist"));
        List<Line> lines = table.getLines();
        Set<Long> idsToDelete = new HashSet<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                if (lines.get(i).equals(lines.get(j))) {
                    idsToDelete.add(lines.get(j).getId());
                }
            }
        }
        idsToDelete.stream().forEach(id -> lineService.deleteLine(id));
    }
}
