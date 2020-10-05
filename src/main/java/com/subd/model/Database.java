package com.subd.model;

import lombok.Data;

import java.util.List;

@Data
public class Database {
    private List<Table> tables;
}
