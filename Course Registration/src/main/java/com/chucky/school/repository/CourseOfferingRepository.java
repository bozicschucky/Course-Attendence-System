package com.chucky.school.repository;

import com.chucky.school.domain.CourseOffering;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long>{

}
