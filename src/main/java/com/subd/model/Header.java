package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private List<Attribute> attributes;
}
