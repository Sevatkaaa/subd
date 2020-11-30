package com.subd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Header header;
    private String name;
    private Type type;
    private Integer maxLength;
}
