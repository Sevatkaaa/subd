package com.subd.data;

import com.subd.model.LineObject;
import lombok.Data;

@Data
public class LineObjectData {
    private Long id;
    private String name;
    private Object value;

    public static LineObjectData from(LineObject lineObject) {
        if (lineObject == null) {
            return null;
        }
        LineObjectData lineObjectData = new LineObjectData();
        lineObjectData.setId(lineObject.getId());
        lineObjectData.setName(lineObject.getName());
        lineObjectData.setValue(lineObject.getValue());
        return lineObjectData;
    }
}
