package com.subd.service;

import com.subd.dao.LineObjectRepository;
import com.subd.model.LineObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LineObjectService {

    @Resource
    private LineObjectRepository lineObjectRepository;

    public LineObject editLineObject(Long id, String newValue) {
        LineObject lineObject = lineObjectRepository.findById(id).orElseThrow(() -> new RuntimeException("line object not found"));
        lineObject.setValue(newValue);
        return lineObjectRepository.save(lineObject);
    }
}
