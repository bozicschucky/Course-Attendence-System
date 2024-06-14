package com.chucky.school.repository;
import com.chucky.school.domain.Student;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
