package com.subd.controller;

import com.subd.data.DbTableData;
import com.subd.data.LineData;
import com.subd.model.DbTable;
import com.subd.model.Line;
import com.subd.service.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TableController {

    @Resource
    private TableService tableService;

    @RequestMapping(method = RequestMethod.GET, value = "/tables/{id}")
    public ResponseEntity<DbTableData> getTable(@PathVariable Long id) {
        DbTable table = tableService.getTable(id);
        DbTableData tableData = DbTableData.from(table);
        return ResponseEntity.ok(tableData);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tables")
    public ResponseEntity<List<DbTableData>> getTables() {
        List<DbTable> tables = tableService.getTables();
        List<DbTableData> tableDatas = tables.stream().map(DbTableData::from).collect(Collectors.toList());
        return ResponseEntity.ok(tableDatas);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/table")
    public ResponseEntity<DbTableData> createTable(@RequestParam String name, @RequestParam Long dbId) {
        DbTable table = tableService.createTable(name, dbId);
        DbTableData tableData = DbTableData.from(table);
        return ResponseEntity.ok(tableData);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/table")
    public ResponseEntity<DbTableData> deleteTable(@RequestParam Long tableId) {
        tableService.deleteTable(tableId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/intersect")
    public ResponseEntity<List<LineData>> getIntersectOfTables(@RequestParam Long tableId1, @RequestParam Long tableId2) {
        List<Line> lines1 = tableService.getTable(tableId1).getLines();
        List<Line> lines2 = tableService.getTable(tableId2).getLines();
        Set<Line> result = lines1.stream()
                .distinct()
                .filter(lines2::contains)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(result.stream()
                .map(LineData::from)
                .collect(Collectors.toList()));
    }
}
