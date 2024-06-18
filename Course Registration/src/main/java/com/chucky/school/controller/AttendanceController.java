package com.chucky.school.controller;


import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.domain.Student;
import com.chucky.school.service.AttendanceService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/create")
    public ResponseEntity<?> createAttendance(
                                           @RequestParam Long studentId,
                                           @RequestParam Long locationId){
        AttendanceRecordDTO attendanceRecordDTO = attendanceService.createAttendanceRecord(studentId,locationId);
        return ResponseEntity.ok(attendanceRecordDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendance(@PathVariable Long id){
        AttendanceRecordDTO attendanceRecord = attendanceService.getAttendance(id);
        return ResponseEntity.ok(attendanceRecord);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAttendance(){
        Collection<AttendanceRecordDTO> attendanceRecords = attendanceService.getAllAttendanceRecords();
        return ResponseEntity.ok(attendanceRecords);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id){
        attendanceService.deleteAttendanceRecord(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable long id,
    @RequestParam Long studentId,
    @RequestParam Long locationId){
        AttendanceRecordDTO attendanceRecord1 = attendanceService.updateAttendanceRecord(
                id,studentId,locationId);
        return ResponseEntity.ok(attendanceRecord1);
    }
}
