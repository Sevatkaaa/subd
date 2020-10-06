package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "header")
@Data
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "header", cascade = CascadeType.REMOVE)
    private List<Attribute> attributes;
}
