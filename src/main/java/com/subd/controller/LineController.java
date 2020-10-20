package com.subd.controller;

import com.subd.data.LineData;
import com.subd.model.Line;
import com.subd.service.LineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
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

    @RequestMapping(method = RequestMethod.PUT, value = "/line")
    public ResponseEntity<LineData> editLine(@RequestParam Long tableId, @RequestParam Long lineId, @RequestBody List<Map<String, String>> data) {
        Line line = lineService.editLine(tableId, lineId, data);
        LineData lineData = LineData.from(line);
        return ResponseEntity.ok(lineData);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/line")
    public ResponseEntity<LineData> deleteLine(@RequestParam Long lineId) {
        lineService.deleteLine(lineId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/linesByLineObjName")
    public ResponseEntity<List<LineData>> getLinesByLineObjName(@RequestParam String lineObjName) {
        List<Line> linesByLineObjectName = lineService.getLinesByLineObjectName(lineObjName);
        List<LineData> lines = linesByLineObjectName.stream().map(LineData::from).collect(Collectors.toList());
        return ResponseEntity.ok(lines);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/linesByLineObjValue")
    public ResponseEntity<List<LineData>> getLinesByLineObjValue(@RequestParam String lineObjValue, @RequestParam Long tableId) {
        List<Line> linesByLineObjectValue = lineService.getLinesByLineObjectValue(lineObjValue, tableId);
        List<LineData> lines = linesByLineObjectValue.stream().map(LineData::from).collect(Collectors.toList());
        return ResponseEntity.ok(lines);
    }
}
