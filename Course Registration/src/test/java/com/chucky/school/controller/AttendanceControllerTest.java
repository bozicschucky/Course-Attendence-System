//package com.chucky.school.controller;
//
//import com.chucky.school.DTO.AttendanceRecordDTO;
//import com.chucky.school.service.AttendanceService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class AttendanceControllerTest {
//
//    @Mock
//    private AttendanceService attendanceService;
//
//    @InjectMocks
//    private AttendanceController attendanceController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateAttendance() {
//        Long studentId = 1L;
//        Long locationId = 1L;
//        AttendanceRecordDTO attendanceRecordDTO = new AttendanceRecordDTO();
//        when(attendanceService.createAttendanceRecord(studentId, locationId)).thenReturn(attendanceRecordDTO);
//
//        ResponseEntity<?> responseEntity = attendanceController.createAttendance(studentId, locationId);
//
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(attendanceRecordDTO, responseEntity.getBody());
//    }
//
//    @Test
//    public void testGetAttendance() {
//        Long id = 1L;
//        AttendanceRecordDTO attendanceRecordDTO = new AttendanceRecordDTO();
//        when(attendanceService.getAttendance(id)).thenReturn(attendanceRecordDTO);
//
//        ResponseEntity<?> responseEntity = attendanceController.getAttendance(id);
//
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(attendanceRecordDTO, responseEntity.getBody());
//    }
//
//    @Test
//    public void testGetAllAttendance() {
//        Collection<AttendanceRecordDTO> attendanceRecords = Arrays.asList(new AttendanceRecordDTO(), new AttendanceRecordDTO());
//        when(attendanceService.getAllAttendanceRecords()).thenReturn(attendanceRecords);
//
//        ResponseEntity<?> responseEntity = attendanceController.getAllAttendance();
//
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(attendanceRecords, responseEntity.getBody());
//    }
//
//    @Test
//    public void testDeleteAttendance() {
//        Long id = 1L;
//        doNothing().when(attendanceService).deleteAttendanceRecord(id);
//
//        ResponseEntity<?> responseEntity = attendanceController.deleteAttendance(id);
//
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        verify(attendanceService, times(1)).deleteAttendanceRecord(id);
//    }
//
//    @Test
//    public void testUpdateAttendance() {
//        Long id = 1L;
//        Long studentId = 1L;
//        Long locationId = 1L;
//        AttendanceRecordDTO attendanceRecordDTO = new AttendanceRecordDTO();
//        when(attendanceService.updateAttendanceRecord(id, studentId, locationId)).thenReturn(attendanceRecordDTO);
//
//        ResponseEntity<?> responseEntity = attendanceController.updateAttendance(id, studentId, locationId);
//
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(attendanceRecordDTO, responseEntity.getBody());
//    }
//}
