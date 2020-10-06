package com.subd.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineObject that = (LineObject) o;
        return Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, value);
    }
}
