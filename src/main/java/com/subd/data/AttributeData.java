package com.subd.data;

import com.subd.model.Attribute;
import lombok.Data;

import static java.util.Optional.ofNullable;

@Data
public class AttributeData {
    private Long id;
    private String name;
    private String type;
    private Integer maxLength;

    public static AttributeData from(Attribute attribute) {
        if (attribute == null) {
            return null;
        }
        AttributeData attributeData = new AttributeData();
        attributeData.setId(attribute.getId());
        attributeData.setName(attribute.getName());
        attributeData.setType(ofNullable(attribute.getType()).map(Object::toString).orElse(null));
        attributeData.setMaxLength(attribute.getMaxLength());
        return attributeData;
    }
}
