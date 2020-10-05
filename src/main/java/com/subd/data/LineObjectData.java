package com.subd.data;

import com.subd.model.LineObject;
import lombok.Data;

import static java.util.Optional.ofNullable;

@Data
public class LineObjectData {
    private Long id;
    private String name;
    private String type;
    private String value;

    public static LineObjectData from(LineObject lineObject) {
        if (lineObject == null) {
            return null;
        }
        LineObjectData lineObjectData = new LineObjectData();
        lineObjectData.setId(lineObject.getId());
        lineObjectData.setName(lineObject.getName());
        lineObjectData.setType(ofNullable(lineObject.getType()).map(Object::toString).orElse(null));
        lineObjectData.setValue(lineObject.getValue());
        return lineObjectData;
    }
}
