package com.subd.controller;

import com.subd.data.LineData;
import com.subd.model.Line;
import com.subd.service.LineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LineController {

    @Resource
    private LineService lineService;

    @RequestMapping(method = RequestMethod.POST, value = "/line")
    public ResponseEntity<LineData> addLineToTable(@RequestParam Long tableId, @RequestBody List<Map<String, String>> data) {
        Line line = lineService.addLineToTable(tableId, data);
        LineData lineData = LineData.from(line);
        return ResponseEntity.ok(lineData);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/line")
    public ResponseEntity<LineData> deleteLine(@RequestParam Long lineId) {
        lineService.deleteLine(lineId);
        return ResponseEntity.ok().build();
    }
}
