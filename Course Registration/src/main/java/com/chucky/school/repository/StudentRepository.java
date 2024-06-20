package com.chucky.school.repository;
import com.chucky.school.domain.Student;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    default Student saveStudent(Student student){
        //try {
            return this.save(student);
        //} catch (DataIntegrityViolationException e) {
        //    throw new RuntimeException("Student id '" + student.getStudentId() + "' already exists", e);
        //}

    }

    @Query("Select s from Student s where s.studentId = ?1")
    public Optional<Student> findBystudentID(Long studentID);
}
