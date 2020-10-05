package com.subd.controller;

import com.subd.data.DatabaseData;
import com.subd.model.Database;
import com.subd.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class DatabaseController {

    @Resource
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST, value = "/database")
    public ResponseEntity<DatabaseData> createDatabase(@RequestParam String name) {
        Database database = databaseService.createDatabase(name);
        DatabaseData databaseData = DatabaseData.from(database);
        return ResponseEntity.ok(databaseData);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/databases")
    public ResponseEntity<List<DatabaseData>> getDatabases() {
        List<Database> databases = databaseService.getDatabases();
        List<DatabaseData> databasesData = databases.stream().map(DatabaseData::from).collect(Collectors.toList());
        return ResponseEntity.ok(databasesData);
    }
}
