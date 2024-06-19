package com.chucky.school.repository;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.CourseOffering;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
    CourseOffering getCourseOfferingById(long id);

    @Query("SELECT new com.chucky.school.DTO.CourseOfferingDetailsDTO(co.id, co.course.id, co.faculty.id, co.capacity, co.courseOfferingType, co.room, co.startDate, co.endDate) FROM CourseOffering co")
    List<CourseOfferingDetailsDTO> getCourseOfferingDetails();

    @Query("SELECT new com.chucky.school.DTO.CourseOfferingDetailsDTO(co.id, co.course.id, co.faculty.id, co.capacity, co.courseOfferingType, co.room, co.startDate, co.endDate) FROM CourseOffering co where co.id=:courseOfferingId")
    CourseOfferingDetailsDTO getCourseOfferingDTOById(@Param("courseOfferingId") long courseOfferingId);

    @Query("SELECT c.course FROM CourseOffering c WHERE c.startDate <= :date AND c.endDate >= :date")
    List<Course> findCourseOfferingsInSessionOn(LocalDate date);
}
