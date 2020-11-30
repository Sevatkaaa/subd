package com.subd.model;

import lombok.Data;

import javax.persistence.*;

@Data
public class LineObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Type type;
    private String value;
    private Line line;
}
