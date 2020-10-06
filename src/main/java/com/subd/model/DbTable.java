package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl")
@Data
public class DbTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dtbs_id", nullable = false)
    private Database dtbs;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "header_id", unique = true)
    private Header header;
    @OneToMany(mappedBy = "tbl", cascade = CascadeType.REMOVE)
    private List<Line> lines;
}
