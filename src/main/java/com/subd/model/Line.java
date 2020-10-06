package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "line")
@Data
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tbl_id", nullable = false)
    private DbTable tbl;
    @OneToMany(mappedBy = "line", cascade = CascadeType.REMOVE)
    private List<LineObject> lineObjects;
}
