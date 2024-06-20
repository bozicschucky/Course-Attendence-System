package com.chucky.school;

import com.chucky.school.Integration.Scheduler;
import com.chucky.school.domain.*;
import com.chucky.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.chucky.school.repository")
@EntityScan(basePackages = "com.chucky.school.domain")
@Transactional
public class SchoolApplication implements CommandLineRunner {

	@Autowired
	LocationRepository locationRepository;

	public static void main(String[] args) {

		SpringApplication.run(SchoolApplication.class, args);
	}
	@Override

	public void run(String... args) throws Exception {


		Scheduler scheduler = new Scheduler();
		scheduler.scheduleTask(5);
		scheduler.scheduleTask(10);


	}
}




















//		LocationType lectureHall = new LocationType("Lecture Hall", null);
//		LocationType laboratory = new LocationType("Laboratory", null);
//		LocationType seminarRoom = new LocationType("Seminar Room", null);
//
//		// Save LocationTypes
//
//
//		// Create Locations
//		Location location1 = new Location(1L, 100L, "Main Lecture Hall", null, lectureHall);
//		Location location2 = new Location(2L, 30L, "Physics Lab", null, laboratory);
//		Location location3 = new Location(3L, 50L, "Seminar Room A", null, seminarRoom);
//		Location location4 = new Location(1L, 75L, "Secondary Lecture Hall", null, lectureHall);
//		Location location5 = new Location(2L, 25L, "Chemistry Lab", null, laboratory);
//		Location location6 = new Location(3L, 40L, "Seminar Room B", null, seminarRoom);
//
//
//		locationRepository.save(location1);
//		locationRepository.save(location2);
//		locationRepository.save(location3);



//		List<Session> sessions1 = Arrays.asList(
//				new Session(null, LocalDate.of(2024, 6, 3), "Session 1", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 4), "Session 2", sessionTime.AFTERNOON, null),
//				new Session(null, LocalDate.of(2024, 6, 5), "Session 3", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 6), "Session 4", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 7), "Session 5", sessionTime.AFTERNOON, null)
//		);
//
//		// Creating CourseOffering 1
//		CourseOffering courseOffering1 = new CourseOffering(
//				30L,
//				"Lecture",
//				"Room 101",
//				null,
//				sessions1,
//				null, // Assuming Course object is null for this example
//				null, // Assuming Faculty object is null for this example
//				LocalDate.of(2024, 6, 3),
//				LocalDate.of(2024, 6, 28)
//		);
//		sessions1.forEach(session -> session.setCourseOffering(courseOffering1));
//
//		// Creating sessions for CourseOffering 2
//		List<Session> sessions2 = Arrays.asList(
//				new Session(null, LocalDate.of(2024, 6, 10), "Session 1", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 11), "Session 2", sessionTime.AFTERNOON, null),
//				new Session(null, LocalDate.of(2024, 6, 12), "Session 3", sessionTime.AFTERNOON, null),
//				new Session(null, LocalDate.of(2024, 6, 13), "Session 4", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 14), "Session 5", sessionTime.AFTERNOON, null)
//		);
//
//		// Creating CourseOffering 2
//		CourseOffering courseOffering2 = new CourseOffering(
//				25L,
//				"Seminar",
//				"Room 102",
//				null,
//				sessions2,
//				null,
//				null,
//				LocalDate.of(2024, 6, 10),
//				LocalDate.of(2024, 6, 30)
//		);
//		sessions2.forEach(session -> session.setCourseOffering(courseOffering2));
//
//		// Creating sessions for CourseOffering 3
//		List<Session> sessions3 = Arrays.asList(
//				new Session(null, LocalDate.of(2024, 6, 17), "Session 1", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 18), "Session 2", sessionTime.AFTERNOON, null),
//				new Session(null, LocalDate.of(2024, 6, 19), "Session 3", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 20), "Session 4", sessionTime.MORNING, null),
//				new Session(null, LocalDate.of(2024, 6, 21), "Session 5", sessionTime.AFTERNOON, null)
//		);
//
//		// Creating CourseOffering 3
//		CourseOffering courseOffering3 = new CourseOffering(
//				20L,
//				"Workshop",
//				"Room 103",
//				null,
//				sessions3,
//				null,
//				null,
//				LocalDate.of(2024, 6, 17),
//				LocalDate.of(2024, 6, 30)
//		);
//
//
//		courseOfferingRepository.save(courseOffering1);
//		courseOfferingRepository.save(courseOffering2);
//		courseOfferingRepository.save(courseOffering3);















//		List<Staff> staffList = List.of(
//				new Staff("John", "Doe", GenderType.MALE, "john.doe@example.com", LocalDate.of(1980, 1, 15), new AuditData(), "johndoe", "password123", "Administrator"),
//				new Staff("Jane", "Smith", GenderType.FEMALE, "jane.smith@example.com", LocalDate.of(1985, 2, 20), new AuditData(), "janesmith", "password456", "Registrar"),
//				new Staff("Robert", "Johnson", GenderType.MALE, "robert.johnson@example.com", LocalDate.of(1975, 3, 25), new AuditData(), "robertjohnson", "password789", "Counselor"),
//				new Staff("Emily", "Davis", GenderType.FEMALE, "emily.davis@example.com", LocalDate.of(1990, 4, 30), new AuditData(), "emilydavis", "password101", "Librarian"),
//				new Staff("Michael", "Wilson", GenderType.MALE, "michael.wilson@example.com", LocalDate.of(1988, 5, 5), new AuditData(), "michaelwilson", "password202", "Coach"),
//				new Staff("Jessica", "Garcia", GenderType.FEMALE, "jessica.garcia@example.com", LocalDate.of(1983, 6, 10), new AuditData(), "jessicagarcia", "password303", "Nurse"),
//				new Staff("David", "Martinez", GenderType.MALE, "david.martinez@example.com", LocalDate.of(1979, 7, 15), new AuditData(), "davidmartinez", "password404", "Maintenance"),
//				new Staff("Sarah", "Rodriguez", GenderType.FEMALE, "sarah.rodriguez@example.com", LocalDate.of(1992, 8, 20), new AuditData(), "sarahrodriguez", "password505", "Secretary"),
//				new Staff("James", "Hernandez", GenderType.MALE, "james.hernandez@example.com", LocalDate.of(1984, 9, 25), new AuditData(), "jameshernandez", "password606", "Janitor"),
//				new Staff("Laura", "Lopez", GenderType.FEMALE, "laura.lopez@example.com", LocalDate.of(1995, 10, 30), new AuditData(), "lauralopez", "password707", "Receptionist")
//		);
//
//
//		List<Faculty> facultyList = List.of(
//				new Faculty("John", "Doe", GenderType.MALE, "john.doe@faculty.com", LocalDate.of(1980, 1, 15), new AuditData(), "johndoe_faculty", "password123", "Professor", "Dr.", List.of(new FacultyHobby("Reading"), new FacultyHobby("Writing")), null),
//				new Faculty("Jane", "Smith", GenderType.FEMALE, "jane.smith@faculty.com", LocalDate.of(1985, 2, 20), new AuditData(), "janesmith_faculty", "password456", "Associate Professor", "Dr.", List.of(new FacultyHobby("Cooking"), new FacultyHobby("Hiking")), null),
//				new Faculty("Robert", "Johnson", GenderType.MALE, "robert.johnson@faculty.com", LocalDate.of(1975, 3, 25), new AuditData(), "robertjohnson_faculty", "password789", "Assistant Professor", "Dr.", List.of(new FacultyHobby("Gardening"), new FacultyHobby("Fishing")),null),
//				new Faculty("Emily", "Davis", GenderType.FEMALE, "emily.davis@faculty.com", LocalDate.of(1990, 4, 30), new AuditData(), "emilydavis_faculty", "password101", "Lecturer", "Ms.", List.of(new FacultyHobby("Dancing"), new FacultyHobby("Painting")), null),
//				new Faculty("Michael", "Wilson", GenderType.MALE, "michael.wilson@faculty.com", LocalDate.of(1988, 5, 5), new AuditData(), "michaelwilson_faculty", "password202", "Professor", "Dr.", List.of(new FacultyHobby("Photography"), new FacultyHobby("Traveling")), null),
//				new Faculty("Jessica", "Garcia", GenderType.FEMALE, "jessica.garcia@faculty.com", LocalDate.of(1983, 6, 10), new AuditData(), "jessicagarcia_faculty", "password303", "Associate Professor", "Dr.", List.of(new FacultyHobby("Cooking"), new FacultyHobby("Swimming")), null),
//				new Faculty("David", "Martinez", GenderType.MALE, "david.martinez@faculty.com", LocalDate.of(1979, 7, 15), new AuditData(), "davidmartinez_faculty", "password404", "Assistant Professor", "Dr.", List.of(new FacultyHobby("Running"), new FacultyHobby("Cycling")), null),
//				new Faculty("Sarah", "Rodriguez", GenderType.FEMALE, "sarah.rodriguez@faculty.com", LocalDate.of(1992, 8, 20), new AuditData(), "sarahrodriguez_faculty", "password505", "Lecturer", "Ms.", List.of(new FacultyHobby("Knitting"), new FacultyHobby("Yoga")), null),
//				new Faculty("James", "Hernandez", GenderType.MALE, "james.hernandez@faculty.com", LocalDate.of(1984, 9, 25), new AuditData(), "jameshernandez_faculty", "password606", "Professor", "Dr.", List.of(new FacultyHobby("Cooking"), new FacultyHobby("Jogging")), null),
//				new Faculty("Laura", "Lopez", GenderType.FEMALE, "laura.lopez@faculty.com", LocalDate.of(1995, 10, 30), new AuditData(), "lauralopez_faculty", "password707", "Associate Professor", "Dr.", List.of(new FacultyHobby("Writing"), new FacultyHobby("Reading")), null)
//		);
//
//		facultyRepository.saveAll(facultyList);
//
//		List<Student> studentList = List.of(
//				new Student("John", "Doe", GenderType.MALE, "john.doe@student.com", LocalDate.of(2000, 1, 15), new AuditData(), "johndoe_student", "password123", 1001, "2020", 20001, 30001, facultyList.get(0)),
//				new Student("Jane", "Smith", GenderType.FEMALE, "jane.smith@student.com", LocalDate.of(2001, 2, 20), new AuditData(), "janesmith_student", "password456", 1002, "2021", 20002, 30002, facultyList.get(1)),
//				new Student("Robert", "Johnson", GenderType.MALE, "robert.johnson@student.com", LocalDate.of(2002, 3, 25), new AuditData(), "robertjohnson_student", "password789", 1003, "2022", 20003, 30003, facultyList.get(2)),
//				new Student("Emily", "Davis", GenderType.FEMALE, "emily.davis@student.com", LocalDate.of(2003, 4, 30), new AuditData(), "emilydavis_student", "password101", 1004, "2023", 20004, 30004, facultyList.get(3)),
//				new Student("Michael", "Wilson", GenderType.MALE, "michael.wilson@student.com", LocalDate.of(2004, 5, 5), new AuditData(), "michaelwilson_student", "password202", 1005, "2024", 20005, 30005, facultyList.get(4)),
//				new Student("Jessica", "Garcia", GenderType.FEMALE, "jessica.garcia@student.com", LocalDate.of(2000, 6, 10), new AuditData(), "jessicagarcia_student", "password303", 1006, "2020", 20006, 30006, facultyList.get(5)),
//				new Student("David", "Martinez", GenderType.MALE, "david.martinez@student.com", LocalDate.of(2001, 7, 15), new AuditData(), "davidmartinez_student", "password404", 1007, "2021", 20007, 30007, facultyList.get(6)),
//				new Student("Sarah", "Rodriguez", GenderType.FEMALE, "sarah.rodriguez@student.com", LocalDate.of(2002, 8, 20), new AuditData(), "sarahrodriguez_student", "password505", 1008, "2022", 20008, 30008, facultyList.get(7)),
//				new Student("James", "Hernandez", GenderType.MALE, "james.hernandez@student.com", LocalDate.of(2003, 9, 25), new AuditData(), "jameshernandez_student", "password606", 1009, "2023", 20009, 30009, facultyList.get(8)),
//				new Student("Laura", "Lopez", GenderType.FEMALE, "laura.lopez@student.com", LocalDate.of(2004, 10, 30), new AuditData(), "lauralopez_student", "password707", 1010, "2024", 20010, 30010, facultyList.get(9))
//		);
//
//		studentRepository.saveAll(studentList);

