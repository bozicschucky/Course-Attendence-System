package com.chucky.school.controller;


import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.DTO.SessionDTO;
import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.domain.Student;
import com.chucky.school.service.AttendanceService;
import com.chucky.school.service.AttendanceServiceImplm;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/student-view/attendance-record")
public class AttendanceController {

    @Autowired
    private AttendanceServiceImplm attendanceServiceImplm;

    @PostMapping("/create")
    public ResponseEntity<?> createAttendance(
                                              @RequestParam long studentId,
                                              @RequestParam long locationId) {
        AttendanceRecord attendanceRecord = attendanceServiceImplm.createAttendanceRecord(studentId, locationId);
        return ResponseEntity.ok(attendanceRecord);
    }




    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendance(@PathVariable Long id){
        AttendanceRecordDTO attendanceRecord = attendanceServiceImplm.getAttendanceRecord(id);
        return ResponseEntity.ok(attendanceRecord);
    }

    @GetMapping
    public ResponseEntity<?> getAllAttendance(){
        Collection<AttendanceRecordDTO> attendanceRecords = attendanceServiceImplm.getAttendanceRecords();
//        Collection<AttendanceRecordDTO> attendanceRecords =

        return ResponseEntity.ok().body(Map.of("attendanceRecords",attendanceRecords));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id){
        attendanceServiceImplm.deleteAttendanceRecord(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable long id,
    @RequestBody AttendanceRecord attendanceRecord){
        AttendanceRecord attendanceRecord1 = attendanceServiceImplm.updateAttendanceRecord(id, attendanceRecord);
        return ResponseEntity.ok(attendanceRecord1);
    }



}
