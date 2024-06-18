package com.chucky.school.repository;
import com.chucky.school.domain.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    @Query("Select s from Student s where s.studentId = ?1")
    public Optional<Student> findBystudentID(Long studentID);

}
