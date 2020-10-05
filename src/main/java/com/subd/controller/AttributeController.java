package com.subd.controller;

import com.subd.data.AttributeData;
import com.subd.model.Attribute;
import com.subd.model.Type;
import com.subd.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api")
public class AttributeController {

    @Resource
    private AttributeService attributeService;

    @RequestMapping(method = RequestMethod.POST, value = "/attribute")
    public ResponseEntity<AttributeData> addAttributeToHeader(@RequestParam String name, @RequestParam Type type, @RequestParam Integer maxLength, @RequestParam Long headerId) {
        Attribute attribute = attributeService.addAttributeToHeader(name, type, maxLength, headerId);
        return ResponseEntity.ok(AttributeData.from(attribute));
    }
}
