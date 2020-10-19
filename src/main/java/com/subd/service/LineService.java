package com.subd.service;

import com.subd.dao.LineObjectRepository;
import com.subd.dao.LineRepository;
import com.subd.dao.TableRepository;
import com.subd.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        validateLine(data, table.getHeader());
        Line line = new Line();
        line.setTbl(table);
        List<LineObject> lineObjects = new ArrayList<>();
        List<LineObject> lineObjectsMock = new ArrayList<>();
        line.setLineObjects(lineObjects);
        lineRepository.save(line);
        data.stream().forEach(obj -> {
            LineObject lineObject = new LineObject();
            lineObject.setLine(line);
            lineObject.setName(obj.get("name"));
            lineObject.setType(Type.valueOf(obj.get("type")));
            lineObject.setValue(obj.get("value"));
            lineObjectsMock.add(lineObject);
            lineObjectRepository.save(lineObject);
        });
        line.setLineObjects(lineObjectsMock);
        return line;
    }

    public Line editLine(Long tableId, Long lineId, List<Map<String, String>> data) {
        DbTable table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException("table not found"));
        validateLine(data, table.getHeader());
        Line line = lineRepository.findById(lineId).orElseThrow(() -> new RuntimeException("line not found"));
        data.stream().forEach(obj -> {
            LineObject lineObject = line.getLineObjects().stream().filter(l -> l.getId().equals(Long.valueOf(obj.get("id")))).findFirst().orElseThrow(() -> new RuntimeException("line object not found"));
            lineObject.setValue(obj.get("value"));
            lineObjectRepository.save(lineObject);
        });
        return lineRepository.findById(line.getId()).orElseThrow(() -> new RuntimeException("line not found"));
    }

    public void deleteLine(Long lineId) {
        lineRepository.deleteById(lineId);
    }

    public List<Line> getLinesByLineObjectName(String name) {
        return lineObjectRepository.findByName(name).stream().map(LineObject::getLine).collect(Collectors.toList());
    }

    public List<Line> getLinesByLineObjectValue(String value, Long tableId) {
        return lineObjectRepository.findByValue(value).stream().map(LineObject::getLine).filter(line -> line.getTbl().getId().equals(tableId)).collect(Collectors.toList());
    }

    // need to be moved to another class like Validator to be reused in LineObjectService during data edit
    private void validateLine(List<Map<String, String>> data, Header header) {
        List<String> invalidLines = data.stream().filter(lineObject -> !isValid(lineObject, header)).map(obj -> obj.get("name")).collect(Collectors.toList());
        if (!invalidLines.isEmpty()) {
            throw new RuntimeException("not valid data in attributes " + invalidLines);
        }
    }

    private boolean isValid(Map<String, String> lineObject, Header header) {
        String value = lineObject.get("value");
        Type type = Type.valueOf(lineObject.get("type"));
        String name = lineObject.get("name");
        Attribute attribute = header.getAttributes().stream().filter(at -> at.getName().equals(name)).findAny().orElseThrow(() -> new RuntimeException("not found attribute"));
        if (value.length() > attribute.getMaxLength()) {
            return false;
        }
        if (type == Type.INTEGER) {
            try {
                Integer.parseInt(value);
            } catch (Exception e) {
                return false;
            }
        } else if (type == Type.CHAR) {
            if (value.length() != 1) {
                return false;
            }
        } else if (type == Type.REAL) {
            try {
                Double.parseDouble(value);
            } catch (Exception e) {
                return false;
            }
        } else if (type == Type.COMPLEX_INTEGER) {
            String[] complex = value.split(" ");
            try {
                Integer.parseInt(complex[0]);
                Integer.parseInt(complex[1]);
            } catch (Exception e) {
                return false;
            }
        } else if (type == Type.COMPLEX_REAL) {
            String[] complex = value.split(" ");
            try {
                Double.parseDouble(complex[0]);
                Double.parseDouble(complex[1]);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
