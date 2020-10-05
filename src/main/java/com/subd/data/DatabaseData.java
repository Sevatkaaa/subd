package com.subd.data;

import com.subd.model.Database;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DatabaseData {
    private Long id;
    private String name;
    private List<DbTableData> tables;

    public static DatabaseData from(Database database) {
        DatabaseData databaseData = new DatabaseData();
        databaseData.setId(database.getId());
        databaseData.setName(database.getName());
        if (database.getTables() != null) {
            databaseData.setTables(database.getTables().stream().map(DbTableData::from).collect(Collectors.toList()));
        }
        return databaseData;
    }
}
