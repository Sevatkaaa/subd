package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(new ArrayList<>(lineObjects), new ArrayList<>(line.lineObjects));
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineObjects);
    }
}
