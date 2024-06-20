package com.chucky.school.service;

import com.chucky.school.DTO.LocationDTO;
import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.domain.Location;
import com.chucky.school.domain.Session;
import com.chucky.school.domain.Student;
import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.repository.AttendanceRecordRepository;
import com.chucky.school.repository.LocationRepository;
import com.chucky.school.repository.SessionRepository;
import com.chucky.school.repository.StudentRepository;
import com.chucky.school.service.AttendanceService;
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
    @Override
    public AttendanceRecordDTO createAttendanceRecord(Long studentId, Long locationId) {
        LocalDateTime scanDateTime = LocalDateTime.now();
        LocalDate sessionDate = scanDateTime.toLocalDate();

        LocalDateTime startOfTheDay = scanDateTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfTheDay = scanDateTime.toLocalDate().atTime(LocalTime.MAX);

        Session session = sessionRepository.findBySessionDate(sessionDate)
                .orElseGet(() -> {
                    Session newSession = new Session();
                    newSession.setSessionDate(sessionDate);
                    return sessionRepository.save(newSession);
                });

//        Optional<AttendanceRecord> existingRecord = attendanceRecordRepository.findExistingRecord(studentId, locationId, startOfTheDay, endOfTheDay);
//        if (existingRecord.isPresent()) {
//            throw new RuntimeException("Attendance record already exists");
//        }
        List<AttendanceRecord> existingRecords = attendanceRecordRepository.findBySession_SessionDateAndStudent_Id(sessionDate, studentId);
        if (!existingRecords.isEmpty()) {
            throw new RuntimeException("Duplicate attendance record for student in this session");
        }
        Optional<Student> student = studentRepository.findByStudentId(studentId);


        if (student.isEmpty()) {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found");
        }

        Optional<Location> location = locationRepository.findById(locationId);

        if (location.isEmpty()) {
            throw new EntityNotFoundException("Location with ID " + locationId + " not found");
        }

        Location location1 = location.get();
        AttendanceRecord attendanceRecordOne = new AttendanceRecord(scanDateTime, student.get(), location1,session);

       // attendanceRecordOne.setSession(session);

        attendanceRecordRepository.save(attendanceRecordOne);

        return buildAttendanceRecordDTO(attendanceRecordOne);

    }


    @Override
    public AttendanceRecordDTO getAttendance(Long id) {
        Optional<AttendanceRecord> attendanceRecord1 = attendanceRecordRepository.findById(id);
        if (attendanceRecord1.isEmpty()) {
            throw new EntityNotFoundException("Attendance Record with ID " + id + " not found");
        }
        AttendanceRecord attendanceRecord2 = attendanceRecord1.get();
        return buildAttendanceRecordDTO(attendanceRecord2);
    }

    @Override
    public Collection<AttendanceRecordDTO> getAllAttendanceRecords() {

        Collection<AttendanceRecordDTO> attendanceRecords = new ArrayList<>();
        for(AttendanceRecord attendanceRecord : attendanceRecordRepository.findAll()){
            AttendanceRecordDTO attendanceRecordDTO = buildAttendanceRecordDTO(attendanceRecord);
            attendanceRecords.add(attendanceRecordDTO);
//            attendanceRecords.add(new AttendanceRecordDTO(
//                    attendanceRecord.getId(),
//                    attendanceRecord.getStudent().getStudentId(),
//                    attendanceRecord.getLocation().getLocationType().getType()
//            ));

        }


        return attendanceRecords;
    }

    @Override
    public void deleteAttendanceRecord(long id) {
        Optional<AttendanceRecord> attendanceRecord = attendanceRecordRepository.findById(id);
        if(attendanceRecord.isEmpty()) {
            throw new EntityNotFoundException("Attendance Record with ID " + id + " not found");
        }

        attendanceRecordRepository.deleteById(id);
    }

    @Override
    public AttendanceRecordDTO updateAttendanceRecord(long id, Long studentId, Long locationId) {

        Optional<AttendanceRecord> attendanceRecord1 = attendanceRecordRepository.findById(id);
        if(attendanceRecord1.isEmpty()) {
            throw new EntityNotFoundException("Attendance Record with ID " + id + " not found");
        }
        Optional<Student> student = studentRepository.findByStudentId(studentId);

        if (student.isEmpty()) {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found");
        }

        Optional<Location> location = locationRepository.findById(locationId);

        if (location.isEmpty()) {
            throw new EntityNotFoundException("Location with ID " + locationId + " not found");
        }

        AttendanceRecord updatedAttendanceRecord = attendanceRecord1.get();
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
                location2.getCapacity(),
                location2.getName(),
                location2.getLocationType().getType()
        );

        return new AttendanceRecordDTO(
                attendanceRecord.getId(),
                attendanceRecord.getStudent().getStudentId(),
                attendanceRecord.getStudent().getFirstName(),
                attendanceRecord.getStudent().getLastName(),
                locationDTO
        );
    }



}
