package com.subd.model;

import lombok.Data;

import java.util.List;

@Data
public class Table {
    private String name;
    private Header header;
    private List<Line> lines;
}
