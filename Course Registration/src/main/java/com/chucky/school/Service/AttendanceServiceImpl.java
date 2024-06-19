package com.chucky.school.service;

import com.chucky.school.DTO.LocationDTO;
import com.chucky.school.DTO.SessionDTO;
import com.chucky.school.domain.*;
import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.repository.AttendanceRecordRepository;
import com.chucky.school.repository.LocationRepository;
import com.chucky.school.repository.SessionRepository;
import com.chucky.school.repository.StudentRepository;
import com.chucky.school.service.AttendanceService;
import exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private LocationRepository locationRepository;

    public AttendanceRecordDTO createAttendanceRecord(long sessionId, long studentId, long locationId) {

        LocalDateTime scanDateAndTime = LocalDateTime.now();
        LocalTime scanDateTime = scanDateAndTime.toLocalTime();
        LocalDate sessionDate = scanDateAndTime.toLocalDate();

        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isEmpty()) {
            throw new ResourceNotFoundException("Student with ID " + studentId + " not found");
        }

        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isEmpty()) {
            throw new ResourceNotFoundException("Location with ID " + locationId + " not found");
        }

        Session session = sessionRepository.findById(sessionId).orElseThrow(() ->
                new ResourceNotFoundException("Session with ID " + sessionId + " not found")
        );



        DayTime dayTime;
        if (scanDateTime.isAfter(LocalTime.of(6, 0)) && scanDateTime.isBefore(LocalTime.of(12, 30))) {
            dayTime = DayTime.MORNING;
        } else if (scanDateTime.isAfter(LocalTime.of(13, 30)) && scanDateTime.isBefore(LocalTime.of(15, 30))) {
            dayTime = DayTime.AFTERNOON;
        } else {
            throw new ResourceNotFoundException("Attendance record cannot be created for this time");
        }

        for (AttendanceRecord attendanceRecord : session.getAttendanceRecords()) {
            if (attendanceRecord.getStudent().getId() == (studentId) &&
                    attendanceRecord.getLocation().getId()== (locationId) &&
                    attendanceRecord.getDayTime() == dayTime) {
                throw new ResourceNotFoundException("Duplicate attendance record for student in this session");
            }
        }

        AttendanceRecord attendanceRecord = new AttendanceRecord(scanDateAndTime, student.get(),dayTime, location.get());
        attendanceRecordRepository.save(attendanceRecord);
        session.addAttendanceRecord(attendanceRecord);
        sessionRepository.save(session);

        return buildAttendanceRecordDTO(attendanceRecord);
    }

//    public AttendanceRecordDTO createAttendanceRecord(long sessionId,long studentId, long locationId) {
//
//        LocalDateTime scanDateAndTime = LocalDateTime.now();
//        LocalTime scanDateTime = scanDateAndTime.toLocalTime();
//        LocalDate sessionDate = scanDateAndTime.toLocalDate();
//
//        Optional<Student> student = studentRepository.findById(studentId);
//
//        if (student.isEmpty()) {
//            throw new ResourceNotFoundException("Student with ID " + studentId + " not found");
//        }
//        Optional<Location> location = locationRepository.findById(locationId);
//        if (location.isEmpty()) {
//            throw new ResourceNotFoundException("Location with ID " + locationId + " not found");
//        }
//
//        Session session = sessionRepository.findById(sessionId).get();
//
//        for(AttendanceRecord attendanceRecord : session.getAttendanceRecords()) {
//            if(attendanceRecord.getStudent().getId() == studentId && attendanceRecord.getLocation().getId() == locationId && attendanceRecord.getDayTime() == DayTime.MORNING){
//
//            }
//        }
//
//        if (scanDateTime.isAfter(LocalTime.of(6, 0)) && scanDateTime.isBefore(LocalTime.of(12, 30))) {
//
//            for (AttendanceRecord attendanceRecord : session.getAttendanceRecords()) {
//                if (attendanceRecord.getStudent().getId() == studentId && attendanceRecord.getLocation().getId() == locationId && attendanceRecord.getDayTime() == DayTime.MORNING) {
//                    throw new ResourceNotFoundException("Duplicate attendance record for student in this session");
//                }
//            }
//            AttendanceRecord attendanceRecordOne = new AttendanceRecord(scanDateAndTime, student.get(), DayTime.MORNING, location.get());
//
//            attendanceRecordRepository.save(attendanceRecordOne);
//            session.addAttendanceRecord(attendanceRecordOne);
//            sessionRepository.save(session);
//            return buildAttendanceRecordDTO(attendanceRecordOne);
//        } else if (scanDateTime.isAfter(LocalTime.of(1, 30)) && scanDateTime.isBefore(LocalTime.of(3, 30))) {
//
//            for (AttendanceRecord attendanceRecord : session.getAttendanceRecords()) {
//                if (attendanceRecord.getStudent().getStudentId() == studentId && attendanceRecord.getLocation().getId() == locationId && attendanceRecord.getDayTime() == DayTime.AFTERNOON) {
//                    throw new ResourceNotFoundException("Duplicate attendance record for student in this session");
//                }
//            }
//
//            AttendanceRecord attendanceRecordOne = new AttendanceRecord(scanDateAndTime, student.get(), DayTime.AFTERNOON, location.get());
//            attendanceRecordRepository.save(attendanceRecordOne);
//            session.addAttendanceRecord(attendanceRecordOne);
//            sessionRepository.save(session);
//            return buildAttendanceRecordDTO(attendanceRecordOne);
//        } else {
//            throw new ResourceNotFoundException("Attendance record cannot be created for this time");
//        }
//
//
//    }




    public Collection<SessionDTO> getAllSessions() {
        Collection<SessionDTO> sessions = new ArrayList<>();
        for (Session session : sessionRepository.findAll()) {
            sessions.add(buildSessionDTO(session));
        }
        return sessions;
    }





    @Override
    public AttendanceRecordDTO getAttendance(long id) {
        Optional<AttendanceRecord> attendanceRecord = attendanceRecordRepository.findById(id);
        if (attendanceRecord.isEmpty()) {
            throw new ResourceNotFoundException("Attendance Record with ID " + id + " not found");
        }
        AttendanceRecord attendanceRecord2 = attendanceRecord.get();
        return buildAttendanceRecordDTO(attendanceRecord2);
    }

    @Override
    public Collection<AttendanceRecordDTO> getAllAttendanceRecords() {

        Collection<AttendanceRecordDTO> attendanceRecords = new ArrayList<>();
        for(AttendanceRecord attendanceRecord : attendanceRecordRepository.findAll()){
            AttendanceRecordDTO attendanceRecordDTO = buildAttendanceRecordDTO(attendanceRecord);
            attendanceRecords.add(attendanceRecordDTO);
        }


        return attendanceRecords;
    }

    @Override
    public void deleteAttendanceRecord(long id) {
        Optional<AttendanceRecord> attendanceRecord = attendanceRecordRepository.findById(id);
        if(attendanceRecord.isEmpty()) {
            throw new ResourceNotFoundException("Attendance Record with ID " + id + " not found");
        }

        attendanceRecordRepository.deleteById(id);
    }

    @Override
    public AttendanceRecordDTO updateAttendanceRecord(long id, long studentId, long locationId) {

        Optional<AttendanceRecord> attendanceRecord = attendanceRecordRepository.findById(id);
        if(attendanceRecord.isEmpty()) {
            throw new ResourceNotFoundException("Attendance Record with ID " + id + " not found");
        }
        Optional<Student> student = studentRepository.findByStudentId(studentId);

        if (student.isEmpty()) {
            throw new ResourceNotFoundException("Student with ID " + studentId + " not found");
        }

        Optional<Location> location = locationRepository.findById(locationId);

        if (location.isEmpty()) {
            throw new ResourceNotFoundException("Location with ID " + locationId + " not found");
        }

        AttendanceRecord updatedAttendanceRecord = attendanceRecord.get();

           updatedAttendanceRecord.setStudent(student.get());
           updatedAttendanceRecord.setLocation(location.get());
           updatedAttendanceRecord.setScanDateTime(LocalDateTime.now());

           attendanceRecordRepository.save(updatedAttendanceRecord);

           return buildAttendanceRecordDTO(updatedAttendanceRecord);
    }

    private AttendanceRecordDTO buildAttendanceRecordDTO(AttendanceRecord attendanceRecord) {
        Location location2 = attendanceRecord.getLocation();
        LocationDTO locationDTO = new LocationDTO(
                location2.getTypeId(),
                location2.getName(),
                location2.getLocationType().getType()
        );

        return new AttendanceRecordDTO(
                attendanceRecord.getId(),
                attendanceRecord.getScanDateTime(),
                attendanceRecord.getStudent().getStudentId(),
                attendanceRecord.getStudent().getFirstName(),
                attendanceRecord.getStudent().getLastName(),
                locationDTO
        );
    }

    private SessionDTO buildSessionDTO(Session session) {
         String status = null;
//        if (session.getSessionEndDate().isBefore(LocalDate.now())) {
//            status = "Ended";
//        }else {
//            status = "Open";
//        }
        return new SessionDTO(
                session.getId(),
                session.getSessionTitle(),
                session.getSessionDate(),
                session.getStartTime(),
                session.getEndTime(), status

        );
    }



}
