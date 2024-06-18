package com.chucky.school.repository;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.Faculty;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>{
    Faculty findFacultiesById (long id);
}
