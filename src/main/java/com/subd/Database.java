package com.subd;

import lombok.Data;

import java.util.List;

@Data
public class Database {
    private List<Table> tables;
}
