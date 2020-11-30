package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class DbTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Database dtbs;
    private String name;
    private Header header;
    private List<Line> lines;
}
