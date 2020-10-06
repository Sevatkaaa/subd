package com.subd.service;

import com.subd.dao.LineObjectRepository;
import com.subd.dao.LineRepository;
import com.subd.dao.TableRepository;
import com.subd.model.DbTable;
import com.subd.model.Line;
import com.subd.model.LineObject;
import com.subd.model.Type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LineService {

    @Resource
    private TableRepository tableRepository;

    @Resource
    private LineRepository lineRepository;

    @Resource
    private LineObjectRepository lineObjectRepository;

    public Line addLineToTable(Long tableId, List<Map<String, String>> data) {
        DbTable table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException("table not found"));
        Line line = new Line();
        line.setTbl(table);
        List<LineObject> lineObjects = new ArrayList<>();
        line.setLineObjects(lineObjects);
        lineRepository.save(line);
        data.stream().forEach(obj -> {
            LineObject lineObject = new LineObject();
            lineObject.setLine(line);
            lineObject.setName(obj.get("name"));
            lineObject.setType(Type.valueOf(obj.get("type")));
            lineObject.setValue(obj.get("value"));
            lineObjectRepository.save(lineObject);
        });
        return lineRepository.findById(line.getId()).orElseThrow(() -> new RuntimeException("line not found"));
    }

    public void deleteLine(Long lineId) {
        lineRepository.deleteById(lineId);
    }
}
