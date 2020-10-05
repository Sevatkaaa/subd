package com.subd.data;

import com.subd.model.DbTable;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DbTableData {
    private Long id;
    private String name;
    private HeaderData header;
    private List<LineData> lines;

    public static DbTableData from(DbTable table) {
        DbTableData tableData = new DbTableData();
        tableData.setId(table.getId());
        tableData.setName(table.getName());
        tableData.setHeader(HeaderData.from(table.getHeader()));
        if (table.getLines() != null) {
            tableData.setLines(table.getLines().stream().map(LineData::from).collect(Collectors.toList()));
        }
        return tableData;
    }
}
