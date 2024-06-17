package com.chucky.school.repository;

import com.chucky.school.domain.CourseOffering;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
    @Query("SELECT cof FROM CourseOffering cof WHERE cof.course.id=:courseID")
    public List<CourseOffering> getCourseOfferingByCourseID(@Param("courseID") Long courseID);

}
