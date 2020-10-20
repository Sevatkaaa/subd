package com.subd.controller;

import com.subd.data.DatabaseData;
import com.subd.data.DbTableData;
import com.subd.model.Database;
import com.subd.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
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

    @RequestMapping(method = RequestMethod.GET, value = "/databases/{id}")
    public ResponseEntity<DatabaseData> getDatabase(@PathVariable Long id) {
        Database database = databaseService.getDatabase(id);
        DatabaseData databaseData = DatabaseData.from(database);
        return ResponseEntity.ok(databaseData);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/databases/{id}")
    public ResponseEntity<DatabaseData> deleteDatabase(@PathVariable Long id) {
        databaseService.deleteDatabase(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/databases/{id}/tables")
    public ResponseEntity<List<DbTableData>> getDatabaseTables(@PathVariable Long id) {
        Database database = databaseService.getDatabase(id);
        DatabaseData databaseData = DatabaseData.from(database);
        return ResponseEntity.ok(databaseData.getTables());
    }

}
