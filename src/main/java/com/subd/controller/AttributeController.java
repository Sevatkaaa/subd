package com.subd.controller;

import com.subd.data.AttributeData;
import com.subd.model.Attribute;
import com.subd.model.Type;
import com.subd.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "http://localhost:3000")
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
