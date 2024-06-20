package com.chucky.school.controller;

import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.service.AttendanceServiceImplm;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AttendanceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AttendanceServiceImplm attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceController).build();
    }

    @Test
    public void testCreateAttendance() throws Exception {
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setId(1L);
        attendanceRecord.setScanDateTime(LocalDateTime.now());

        when(attendanceService.createAttendanceRecord(anyLong(), anyLong())).thenReturn(attendanceRecord);

        mockMvc.perform(post("/student-view/attendance-record/create")
                        .param("studentId", "1")
                        .param("locationId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(attendanceRecord.getId()));
    }

    @Test
    public void testGetAttendance() throws Exception {
        AttendanceRecordDTO attendanceRecordDTO = new AttendanceRecordDTO();
        attendanceRecordDTO.setId(1L);

        when(attendanceService.getAttendanceRecord(anyLong())).thenReturn(attendanceRecordDTO);

        mockMvc.perform(get("/student-view/attendance-record/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(attendanceRecordDTO.getId()));
    }

    @Test
    public void testGetAllAttendance() throws Exception {
        AttendanceRecordDTO attendanceRecordDTO1 = new AttendanceRecordDTO();
        attendanceRecordDTO1.setId(1L);
        AttendanceRecordDTO attendanceRecordDTO2 = new AttendanceRecordDTO();
        attendanceRecordDTO2.setId(2L);

        Collection<AttendanceRecordDTO> attendanceRecords = List.of(attendanceRecordDTO1, attendanceRecordDTO2);

        when(attendanceService.getAttendanceRecords()).thenReturn(attendanceRecords);

        mockMvc.perform(get("/student-view/attendance-record")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attendanceRecords.length()").value(attendanceRecords.size()));
    }

    @Test
    public void testDeleteAttendance() throws Exception {
        doNothing().when(attendanceService).deleteAttendanceRecord(anyLong());

        mockMvc.perform(delete("/student-view/attendance-record/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(attendanceService, times(1)).deleteAttendanceRecord(anyLong());
    }

    @Test
    public void testUpdateAttendance() throws Exception {
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setId(1L);

        when(attendanceService.updateAttendanceRecord(anyLong(), any(AttendanceRecord.class))).thenReturn(attendanceRecord);

        mockMvc.perform(put("/student-view/attendance-record/update/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"scanDateTime\":\"2024-01-01T12:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(attendanceRecord.getId()));
    }
}
