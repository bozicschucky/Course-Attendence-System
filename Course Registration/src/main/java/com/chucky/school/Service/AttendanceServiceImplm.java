package com.chucky.school.service;

import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.DTO.LocationDTO;
import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.domain.Location;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.AttendanceRecordRepository;
import com.chucky.school.repository.LocationRepository;
import com.chucky.school.repository.SessionRepository;
import com.chucky.school.repository.StudentRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImplm implements AttendanceService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private LocationRepository locationRepository;

    public AttendanceRecord createAttendanceRecord(long studentId, long locationId) {

        LocalDateTime scanDateAndTime = LocalDateTime.now();
        LocalTime scanDateTime = scanDateAndTime.toLocalTime();
        LocalDate sessionDate = scanDateAndTime.toLocalDate();

        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isEmpty()) {
            System.out.println("student is "+studentRepository.findById(studentId));
            throw new ResourceNotFoundException("Student with ID " + studentId + " not found");
        }
        System.out.println("student is "+studentRepository.findById(studentId));

        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isEmpty()) {
            System.out.println("location is "+locationRepository.findById(locationId));
            throw new ResourceNotFoundException("Location with ID " + locationId + " not found");
        }
        System.out.println("location is "+locationRepository.findById(locationId));
        AttendanceRecord attendanceRecord = new AttendanceRecord();

        if ((scanDateTime.isAfter(LocalTime.of(10, 0)) && scanDateTime.isBefore(LocalTime.of(12, 30))) || (scanDateTime.isAfter(LocalTime.of(13, 30)) && scanDateTime.isBefore(LocalTime.of(23, 30)))) {

             attendanceRecord = new AttendanceRecord(scanDateAndTime,student.get(),location.get());
            System.out.println(attendanceRecord);
            attendanceRecordRepository.save(attendanceRecord);
        } else {
            throw new ResourceNotFoundException("Attendance record cannot be created for this time");
        }
   //    attendanceRecordRepository.save(attendanceRecord);

        return attendanceRecord;
    }

    public AttendanceRecord updateAttendanceRecord(long id, AttendanceRecord attendanceRecord) {
        Optional<AttendanceRecord> attendanceRecord1 = attendanceRecordRepository.findById(id);
        if (attendanceRecord1.isEmpty()) {
            throw new ResourceNotFoundException("Attendance record with ID " + id + " not found");
        }
        attendanceRecord1.get().setScanDateTime(attendanceRecord.getScanDateTime());
        attendanceRecord1.get().setStudent(attendanceRecord.getStudent());
        attendanceRecord1.get().setLocation(attendanceRecord.getLocation());
        return attendanceRecordRepository.save(attendanceRecord1.get());
    }

    public AttendanceRecordDTO getAttendanceRecord(long id) {
        Optional<AttendanceRecord> attendanceRecord = attendanceRecordRepository.findById(id);
        if (attendanceRecord.isEmpty()) {
            throw new ResourceNotFoundException("Attendance record with ID " + id + " not found");
        }
        return buildAttendanceRecordDTO(attendanceRecord.get());
    }
    public Collection<AttendanceRecordDTO> getAttendanceRecords() {
        Collection<AttendanceRecordDTO> attendanceRecords = new ArrayList<>();
        for (AttendanceRecord attendanceRecord : attendanceRecordRepository.findAll()) {
            attendanceRecords.add(buildAttendanceRecordDTO(attendanceRecord));
        }
        return attendanceRecords;
    }
    public void deleteAttendanceRecord(long id) {
        Optional<AttendanceRecord> attendanceRecord = attendanceRecordRepository.findById(id);
        if (attendanceRecord.isEmpty()) {
            throw new ResourceNotFoundException("Attendance record with ID " + id + " not found");
        }
        attendanceRecordRepository.delete(attendanceRecord.get());
    }

    private AttendanceRecordDTO buildAttendanceRecordDTO(AttendanceRecord attendanceRecord) {
        Location location = attendanceRecord.getLocation();

            LocationDTO location2 = new LocationDTO(
                    location.getTypeId(),
                    location.getName(),
                    location.getLocationType().getType()
            );

     //   Location location2 = attendanceRecord.getLocation();


        return new AttendanceRecordDTO(
                attendanceRecord.getId(),
                attendanceRecord.getScanDateTime(),
                attendanceRecord.getStudent().getStudentId(),
                attendanceRecord.getStudent().getFirstName(),
                attendanceRecord.getStudent().getLastName(),
                location2
        );
    }

}
