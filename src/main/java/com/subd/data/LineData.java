package com.subd.data;

import com.subd.model.Line;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class LineData {
    private Long id;
    private List<LineObjectData> lineObjects;

    public static LineData from(Line line) {
        LineData lineData = new LineData();
        lineData.setId(line.getId());
        if (line.getLineObjects() != null) {
            lineData.setLineObjects(line.getLineObjects().stream().map(LineObjectData::from).collect(Collectors.toList()));
        }
        return lineData;
    }
}
