package com.subd.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "lineObject")
@Data
public class LineObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String value;
    @ManyToOne
    @JoinColumn(name = "lineObjects", nullable = false)
    private Line line;
}
