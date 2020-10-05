package com.subd.service;

import com.subd.dao.DatabaseRepository;
import com.subd.model.Database;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DatabaseService {

    @Resource
    private DatabaseRepository databaseRepository;

    public Database createDatabase(String name) {
        Database database = new Database();
        database.setName(name);
        return databaseRepository.save(database);
    }

    public List<Database> getDatabases() {
        return databaseRepository.findAll();
    }

    public Database getDatabase(Long id) {
        return databaseRepository.findById(id).orElseThrow(() -> new RuntimeException("No database found"));
    }

    public void deleteDatabase(Long id) {
        databaseRepository.deleteById(id);
    }
}
