package com.chucky.school.service;

import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.DTO.LocationDTO;
import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.domain.Location;
import com.chucky.school.domain.LocationType;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.AttendanceRecordRepository;
import com.chucky.school.repository.LocationRepository;
import com.chucky.school.repository.SessionRepository;
import com.chucky.school.repository.StudentRepository;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AttendanceServiceImplmTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private AttendanceRecordRepository attendanceRecordRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private AttendanceServiceImplm attendanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAttendanceRecord() {
        long studentId = 1L;
        long locationId = 1L;

        // Setup student
        Student student = new Student();
        student.setStudentId(studentId);

        // Setup location and location type
        Location location = new Location();
        location.setId(locationId);
        LocationType locationType = new LocationType();
        location.setLocationType(locationType);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));
        when(attendanceRecordRepository.save(any(AttendanceRecord.class))).thenAnswer((Answer<AttendanceRecord>) invocation -> invocation.getArgument(0));

        // Force the current time to a valid attendance time
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(now);

            AttendanceRecord attendanceRecord = attendanceService.createAttendanceRecord(studentId, locationId);

            assertNotNull(attendanceRecord);
            assertEquals(student, attendanceRecord.getStudent());
            assertEquals(location, attendanceRecord.getLocation());
            verify(attendanceRecordRepository, times(1)).save(any(AttendanceRecord.class));
        }
    }

    @Test
    public void testCreateAttendanceRecordStudentNotFound() {
        long studentId = 1L;
        long locationId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> attendanceService.createAttendanceRecord(studentId, locationId));
    }

    @Test
    public void testCreateAttendanceRecordLocationNotFound() {
        long studentId = 1L;
        long locationId = 1L;
        Student student = new Student();
        student.setStudentId(studentId);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(locationRepository.findById(locationId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> attendanceService.createAttendanceRecord(studentId, locationId));
    }

    @Test
    public void testCreateAttendanceRecordInvalidTime() {
        long studentId = 1L;
        long locationId = 1L;

        Student student = new Student();
        student.setStudentId(studentId);
        Location location = new Location();
        location.setId(locationId);
        LocationType locationType = new LocationType();
        location.setLocationType(locationType);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        // Force the current time to an invalid attendance time
        LocalDateTime invalidTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(invalidTime);

            assertThrows(ResourceNotFoundException.class, () -> attendanceService.createAttendanceRecord(studentId, locationId));
        }
    }

    @Test
    public void testUpdateAttendanceRecord() {
        long id = 1L;
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setId(id);
        Student student = new Student();
        Location location = new Location();
        location.setLocationType(new LocationType());
        attendanceRecord.setStudent(student);
        attendanceRecord.setLocation(location);

        when(attendanceRecordRepository.findById(id)).thenReturn(Optional.of(attendanceRecord));
        when(attendanceRecordRepository.save(any(AttendanceRecord.class))).thenAnswer((Answer<AttendanceRecord>) invocation -> invocation.getArgument(0));

        AttendanceRecord updatedRecord = attendanceService.updateAttendanceRecord(id, attendanceRecord);

        assertEquals(attendanceRecord, updatedRecord);
        verify(attendanceRecordRepository, times(1)).save(any(AttendanceRecord.class));
    }

    @Test
    public void testUpdateAttendanceRecordNotFound() {
        long id = 1L;
        AttendanceRecord attendanceRecord = new AttendanceRecord();

        when(attendanceRecordRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> attendanceService.updateAttendanceRecord(id, attendanceRecord));
    }

    @Test
    public void testGetAttendanceRecord() {
        long id = 1L;
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setId(id);
        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        Location location = new Location();
        location.setLocationType(new LocationType());
        attendanceRecord.setStudent(student);
        attendanceRecord.setLocation(location);

        when(attendanceRecordRepository.findById(id)).thenReturn(Optional.of(attendanceRecord));

        AttendanceRecordDTO dto = attendanceService.getAttendanceRecord(id);

        assertNotNull(dto);
        assertEquals(attendanceRecord.getId(), dto.getId());
        assertEquals(attendanceRecord.getStudent().getStudentId(), dto.getStudentId());
    }

    @Test
    public void testGetAttendanceRecordNotFound() {
        long id = 1L;

        when(attendanceRecordRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> attendanceService.getAttendanceRecord(id));
    }

    @Test
    public void testGetAttendanceRecords() {
        AttendanceRecord attendanceRecord1 = new AttendanceRecord();
        attendanceRecord1.setId(1L);
        attendanceRecord1.setStudent(new Student());
        attendanceRecord1.setLocation(new Location());
        attendanceRecord1.getLocation().setLocationType(new LocationType());

        AttendanceRecord attendanceRecord2 = new AttendanceRecord();
        attendanceRecord2.setId(2L);
        attendanceRecord2.setStudent(new Student());
        attendanceRecord2.setLocation(new Location());
        attendanceRecord2.getLocation().setLocationType(new LocationType());

        List<AttendanceRecord> attendanceRecords = List.of(attendanceRecord1, attendanceRecord2);

        when(attendanceRecordRepository.findAll()).thenReturn(attendanceRecords);

        Collection<AttendanceRecordDTO> attendanceRecordDTOs = attendanceService.getAttendanceRecords();

        assertEquals(attendanceRecords.size(), attendanceRecordDTOs.size());
    }

    @Test
    public void testDeleteAttendanceRecord() {
        long id = 1L;
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setId(id);

        when(attendanceRecordRepository.findById(id)).thenReturn(Optional.of(attendanceRecord));
        doNothing().when(attendanceRecordRepository).delete(attendanceRecord);

        attendanceService.deleteAttendanceRecord(id);

        verify(attendanceRecordRepository, times(1)).delete(attendanceRecord);
    }

    @Test
    public void testDeleteAttendanceRecordNotFound() {
        long id = 1L;

        when(attendanceRecordRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> attendanceService.deleteAttendanceRecord(id));
    }
}
