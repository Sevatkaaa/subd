package com.subd.service;

import com.subd.dao.LineObjectRepository;
import com.subd.model.DbTable;
import com.subd.model.Line;
import com.subd.model.LineObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LineServiceTest {

    private static final String VALUE = "value";
    private static final long TABLE_ID_1 = 1L;
    private static final long TABLE_ID_2 = 2L;

    @InjectMocks
    private LineService lineService;

    @Mock
    private LineObjectRepository lineObjectRepository;

    private DbTable table1;
    private DbTable table2;
    private Line line1;
    private Line line2;
    private Line line3;
    private LineObject line1Object1;
    private LineObject line1Object2;
    private LineObject line2Object1;
    private LineObject line3Object1;

    @Before
    public void setUp() {
        table1 = new DbTable();
        table2 = new DbTable();
        line1 = new Line();
        line2 = new Line();
        line3 = new Line();
        line1Object1 = new LineObject();
        line1Object2 = new LineObject();
        line2Object1 = new LineObject();
        line3Object1 = new LineObject();
        table1.setId(TABLE_ID_1);
        table2.setId(TABLE_ID_2);
        line1Object1.setLine(line1);
        line1Object2.setLine(line1);
        line2Object1.setLine(line2);
        line3Object1.setLine(line3);
        line1.setLineObjects(Arrays.asList(line1Object1, line1Object2));
        line2.setLineObjects(Collections.singletonList(line2Object1));
        line3.setLineObjects(Collections.singletonList(line3Object1));
        line1.setTbl(table1);
        line2.setTbl(table2);
        line3.setTbl(table2);
    }

    @Test
    public void shouldGetLinesFromTable1ByLineObjectValueWhenReturnsThreeLines() {
        when(lineObjectRepository.findByValue(VALUE)).thenReturn(Arrays.asList(line1Object1, line2Object1, line3Object1));

        List<Line> actual = lineService.getLinesByLineObjectValue(VALUE, TABLE_ID_1);

        assertEquals(1, actual.size());
        assertEquals(line1, actual.get(0));
    }

    @Test
    public void shouldGetLinesFromTable2ByLineObjectValueWhenReturnsThreeLines() {
        when(lineObjectRepository.findByValue(VALUE)).thenReturn(Arrays.asList(line1Object1, line2Object1, line3Object1));

        List<Line> actual = lineService.getLinesByLineObjectValue(VALUE, TABLE_ID_2);

        assertEquals(2, actual.size());
        assertEquals(line2, actual.get(0));
        assertEquals(line3, actual.get(1));
    }

    @Test
    public void shouldGetLinesFromTable2ByLineObjectValueWhenReturnsTwoLines() {
        when(lineObjectRepository.findByValue(VALUE)).thenReturn(Arrays.asList(line1Object1, line2Object1));

        List<Line> actual = lineService.getLinesByLineObjectValue(VALUE, TABLE_ID_2);

        assertEquals(1, actual.size());
        assertEquals(line2, actual.get(0));
    }
}
