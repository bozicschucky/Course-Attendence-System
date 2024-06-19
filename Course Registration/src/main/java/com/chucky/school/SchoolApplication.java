package com.chucky.school;

import com.chucky.school.domain.*;
import com.chucky.school.repository.LocationRepository;
import com.chucky.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@SpringBootApplication

@EnableJpaRepositories(basePackages = "com.chucky.school.repository")
@EntityScan(basePackages = "com.chucky.school.domain")
public class SchoolApplication implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	LocationRepository locationRepository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {


//				Faculty faculty2 = new Faculty("kiki", "Nuway", GenderType.MALE, "kiki@miu.edu.com",
//				LocalDate.of(2000, 04,17), null, "kiki","kiki99" , "Faculty", "Dr.", null, null);
//		Student student = new Student("Selam", "kiros", GenderType.FEMALE, "selam@miu.edu", LocalDate.of(1990, 11,18), null, "selam", "beti99", 4L,
//				"2024-10-03", 202L, 200l, faculty2) ;
//		studentRepository.save(student);
//		studentRepository.save(student);
//		System.out.println(studentRepository.findAll());
//		LocationType locationType = new LocationType("classRoom",null);
//	Location location = new Location(39l,100,"Dalby-HAll",null,locationType);
//	locationRepository.save(location);
//		LocationType locationType2 = new LocationType("ConferenceRoom",null);
//		Location location2 = new Location(39l,100,"very-HAll",null,locationType2);
//		locationRepository.save(location);
//		System.out.println(locationRepository.findAll());


	}
}
