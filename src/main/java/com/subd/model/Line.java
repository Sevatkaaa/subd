package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private DbTable tbl;
    private List<LineObject> lineObjects;
}
