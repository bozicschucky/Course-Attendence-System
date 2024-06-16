package com.chucky.school.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.CreatedRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        Course course1 = new Course(3, "Math", "MATH101", "Introduction to Mathematics", "Mathematics Department",
                new CreatedRecord("Admin"));
        Course course2 = new Course(4, "Science", "SCI101", "Introduction to Science", "Science Department",
                new CreatedRecord("Admin"));
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.flush();
    }

    @Test
    public void findByCourseName_ExistingCourse_ReturnsCourse() {
        Course course = courseRepository.findByCourseName("Math");
        assertNotNull(course);
        assertEquals("Math", course.getCourseName());
    }
}