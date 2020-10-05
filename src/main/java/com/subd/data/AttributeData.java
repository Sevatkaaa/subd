package com.subd.data;

import com.subd.model.Attribute;
import lombok.Data;

@Data
public class AttributeData {
    private Long id;
    private String name;
    private Class clazz;
    private Integer maxLength;

    public static AttributeData from(Attribute attribute) {
        AttributeData attributeData = new AttributeData();
        attributeData.setId(attribute.getId());
        attributeData.setName(attribute.getName());
        attributeData.setClazz(attribute.getClazz());
        attributeData.setMaxLength(attribute.getMaxLength());
        return attributeData;
    }
}
