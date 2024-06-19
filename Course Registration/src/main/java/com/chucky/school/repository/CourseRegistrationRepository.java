package com.chucky.school.repository;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.CourseRegistration;
import com.chucky.school.domain.Faculty;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    CourseRegistration findCourseRegistrationByCourseOfferingId(long courseId);
    @Query(" select  COUNT (cr) from CourseRegistration cr where cr.courseOfferingId=:courseOfferingId and cr.studentId=:studentId")
    int countByCourseOfferingIdAndStudentId(@Param("courseOfferingId") long courseId,@Param("studentId") long studentId);

    @Query("SELECT co.capacity, co.courseOfferingType, co.room, co.auditData, co.course, co.faculty, cr.courseOfferingId, cr.studentId, cr.grade FROM CourseRegistration cr JOIN CourseOffering co ON cr.courseOfferingId = co.id WHERE co.id = ?1")
    List<Object[]> getAllfromCourseOffering(long courseOfferingid);




}
