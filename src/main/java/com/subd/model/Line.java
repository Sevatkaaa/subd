package com.subd.model;

import lombok.Data;

import javax.persistence.*;
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
        int size = lineObjects.size();
        if (size != line.lineObjects.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!lineObjects.get(i).equals(line.lineObjects.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineObjects);
    }
}
