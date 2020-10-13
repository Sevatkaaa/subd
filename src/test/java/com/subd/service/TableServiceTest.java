package com.subd.service;

import com.subd.dao.TableRepository;
import com.subd.model.DbTable;
import com.subd.model.Line;
import com.subd.model.LineObject;
import com.subd.model.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TableServiceTest {

    public static final Long TABLE_ID = 1L;

    @InjectMocks
    private TableService tableService;

    @Mock
    private TableRepository tableRepository;

    @Mock
    private LineService lineService;

    private DbTable table;
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private LineObject lineObject1;
    private LineObject lineObject2;
    private LineObject lineObject3;

    @Before
    public void setUp() {
        table = new DbTable();
        line1 = new Line();
        line2 = new Line();
        line3 = new Line();
        line4 = new Line();
        line1.setId(2L);
        line2.setId(3L);
        line3.setId(4L);
        line4.setId(5L);
        table.setId(TABLE_ID);
        lineObject1 = new LineObject();
        lineObject1.setValue("value");
        lineObject1.setType(Type.STRING);
        lineObject1.setName("name");
        lineObject2 = new LineObject();
        lineObject2.setValue("value2");
        lineObject2.setType(Type.STRING);
        lineObject2.setName("name2");
        lineObject3 = new LineObject();
        lineObject2.setValue("value3");
        lineObject2.setType(Type.STRING);
        lineObject2.setName("name3");
        line1.setLineObjects(Collections.singletonList(lineObject1));
        line2.setLineObjects(Collections.singletonList(lineObject1));
        line3.setLineObjects(Collections.singletonList(lineObject1));
        line4.setLineObjects(Collections.singletonList(lineObject1));

        when(tableRepository.findById(TABLE_ID)).thenReturn(Optional.of(table));
    }

    @Test
    public void shouldDeleteTableDuplicatesForTwoEqualsLines() {
        table.setLines(Arrays.asList(line1, line2));

        tableService.deleteTableDuplicates(TABLE_ID);

        verify(lineService, never()).deleteLine(2L);
        verify(lineService).deleteLine(3L);
    }

    @Test
    public void shouldDeleteTableDuplicatesForThreeEqualsLines() {
        table.setLines(Arrays.asList(line1, line2, line3));

        tableService.deleteTableDuplicates(TABLE_ID);

        verify(lineService, never()).deleteLine(2L);
        verify(lineService).deleteLine(3L);
        verify(lineService).deleteLine(4L);
    }

    @Test
    public void shouldDeleteTableDuplicatesForFourEqualsLines() {
        table.setLines(Arrays.asList(line1, line2, line3, line4));

        tableService.deleteTableDuplicates(TABLE_ID);

        verify(lineService, never()).deleteLine(2L);
        verify(lineService).deleteLine(3L);
        verify(lineService).deleteLine(4L);
        verify(lineService).deleteLine(5L);
    }

    @Test
    public void shouldNotDeleteTableDuplicatesForOneLine() {
        table.setLines(Collections.singletonList(line1));

        tableService.deleteTableDuplicates(TABLE_ID);

        verify(lineService, never()).deleteLine(2L);
    }

    @Test
    public void shouldNotDeleteTableDuplicatesForDifferentLines() {
        line2.setLineObjects(Arrays.asList(lineObject1, lineObject2));
        line3.setLineObjects(Collections.singletonList(lineObject2));
        table.setLines(Arrays.asList(line1, line2, line3));

        tableService.deleteTableDuplicates(TABLE_ID);

        verify(lineService, never()).deleteLine(2L);
        verify(lineService, never()).deleteLine(3L);
        verify(lineService, never()).deleteLine(4L);
    }
}