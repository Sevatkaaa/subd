package com.subd.data;

import com.subd.model.Header;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class HeaderData {
    private Long id;
    private List<AttributeData> attributes;

    public static HeaderData from(Header header) {
        HeaderData headerData = new HeaderData();
        headerData.setId(header.getId());
        if (header.getAttributes() != null) {
            headerData.setAttributes(header.getAttributes().stream().map(AttributeData::from).collect(Collectors.toList()));
        }
        return headerData;
    }
}
