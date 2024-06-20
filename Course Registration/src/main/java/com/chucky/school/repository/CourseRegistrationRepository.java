package com.chucky.school.repository;

import com.chucky.school.Adaptor.CourseRegistrationDTO;
import com.chucky.school.domain.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    @Query(" select  COUNT (cr) from CourseRegistration cr where cr.courseOfferingId.id=:courseOfferingId and cr.studentId.id=:studentId")
    int countByCourseOfferingIdAndStudentId(@Param("courseOfferingId") long courseId,@Param("studentId") long studentId);

    @Query("SELECT new com.chucky.school.Adaptor.CourseRegistrationDTO(co.id, co.studentId.id, co.courseOfferingId.id, co.grade) FROM CourseRegistration co where co.id=:courseRegistrationId")
    CourseRegistrationDTO findCourseRegistrationsByCourseOfferingId(@Param("courseRegistrationId")long courseRegistrationId);
    @Query("SELECT new com.chucky.school.Adaptor.CourseRegistrationDTO(co.id, co.studentId.id, co.courseOfferingId.id, co.grade) FROM CourseRegistration co ")
    List<CourseRegistrationDTO> findAllCourseRegistrations();

    @Query("SELECT co.endDate,co.startDate,co.id, cr " +
            "FROM CourseOffering co " +
            "JOIN CourseRegistration cr ON cr.courseOfferingId = co " +
            "WHERE co.id = :courseOfferingId")
    List<Object[]> getAllFromCourseOffering(@Param("courseOfferingId") long courseOfferingId);

    @Query("select cr.courseOfferingId.course.id, cr.courseOfferingId.course.courseCode, cr.courseOfferingId.course.courseName,cr.grade  from CourseRegistration cr where cr.studentId.id=:studentId")
List<Object[]> getAllCourseByStudentId(@Param("studentId") long studentId);

}
