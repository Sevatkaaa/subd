package com.subd;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        // users table with id, name, password, age
        Table users = new Table();
        users.setName("users");
        Header usersHeader = new Header();
        users.setHeader(usersHeader);

        Attribute idAttribute = new Attribute("id", String.class, 20);
        Attribute nameAttribute = new Attribute("name", String.class, 20);
        Attribute passwordAttribute = new Attribute("password", String.class, 20);
        Attribute ageAttribute = new Attribute("age", Integer.class, 20);
        usersHeader.setAttributes(Arrays.asList(idAttribute, nameAttribute, passwordAttribute, ageAttribute));

        Line line1 = new Line();
        Map<String, Object> lineData1 = new HashMap<>();
        lineData1.put("id", "id1");
        lineData1.put("name", "Sov");
        lineData1.put("password", "sov123");
        lineData1.put("age", 19);
        line1.setLineObjects(lineData1);

        Line line2 = new Line();
        Map<String, Object> lineData2 = new HashMap<>();
        lineData2.put("id", "id2");
        lineData2.put("name", "Leo");
        lineData2.put("password", "leo123");
        lineData2.put("age", 20);
        line2.setLineObjects(lineData2);

        users.setLines(Arrays.asList(line1, line2));

        db.setTables(Collections.singletonList(users));

        System.out.println(db);
    }
}
