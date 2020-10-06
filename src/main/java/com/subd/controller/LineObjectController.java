package com.subd.controller;

import com.subd.data.LineObjectData;
import com.subd.model.LineObject;
import com.subd.service.LineObjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class LineObjectController {

    @Resource
    private LineObjectService lineObjectService;

    @RequestMapping(method = RequestMethod.POST, value = "/lineObject")
    public ResponseEntity<LineObjectData> editLineObject(@RequestParam Long id, @RequestParam String newValue) {
        LineObject lineObject = lineObjectService.editLineObject(id, newValue);
        LineObjectData lineObjectData = LineObjectData.from(lineObject);
        return ResponseEntity.ok(lineObjectData);
    }
}
