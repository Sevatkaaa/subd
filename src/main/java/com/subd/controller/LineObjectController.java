package com.subd.controller;

import com.subd.data.LineObjectData;
import com.subd.model.LineObject;
import com.subd.service.LineObjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "http://localhost:3000")
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
