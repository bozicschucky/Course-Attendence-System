package com.chucky.school.repository;

import com.chucky.school.domain.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    CourseRegistration findCourseRegistrationByCourseOfferingId(long courseId);
    @Query(" select  COUNT (cr) from CourseRegistration cr where cr.courseOfferingId=:courseOfferingId and cr.studentId=:studentId")
    int countByCourseOfferingIdAndStudentId(@Param("courseOfferingId") long courseId,@Param("studentId") long studentId);

}
