package com.subd.service;

import com.subd.dao.AttributeRepository;
import com.subd.dao.HeaderRepository;
import com.subd.model.Attribute;
import com.subd.model.Header;
import com.subd.model.Type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttributeService {

    @Resource
    private AttributeRepository attributeRepository;

    @Resource
    private HeaderRepository headerRepository;

    public Attribute addAttributeToHeader(String name, Type type, Integer maxLength, Long headerId) {
        Header header = headerRepository.findById(headerId).orElseThrow(() -> new RuntimeException("header not found"));
        Attribute attribute = new Attribute();
        attribute.setName(name);
        attribute.setType(type);
        attribute.setMaxLength(maxLength);
        attribute.setHeader(header);
        return attributeRepository.save(attribute);
    }
}
