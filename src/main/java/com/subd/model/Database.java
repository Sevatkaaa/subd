package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dtbs")
@Data
public class Database {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "dtbs", cascade = CascadeType.REMOVE)
    private List<DbTable> tables;
}
