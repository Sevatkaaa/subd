package com.subd.reflection;

import com.subd.model.Line;
import com.subd.service.LineService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionRunner {
    public static void main(String[] args) {
        try {
            String className = "com.subd.service.LineService";
            Class<?> clazz = Class.forName(className);
            Constructor<?>[] constructors = clazz.getConstructors();
            Constructor<?> constructor = constructors[0];
            LineService lineService = (LineService) constructor.newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                System.out.println("Field " + declaredFields[i].getName());
            }
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                System.out.println("Method " + declaredMethods[i].getName());
            }
            Method declaredMethod = clazz.getDeclaredMethod("addLineToTable", Long.class, List.class);
            Long tableId = 32L;
            List<Map<String, String>> data = new ArrayList<>();
            data.add(new HashMap<String, String>(){{put("1", "1");}});
            Line result = (Line) declaredMethod.invoke(lineService, tableId, data);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
