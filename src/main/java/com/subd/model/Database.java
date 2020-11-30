package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Database {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private List<DbTable> tables;
}
