package com.chucky.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
<<<<<<< HEAD
@EnableJpaRepositories(basePackages = "com.chucky.school.repository")
@EntityScan(basePackages = "com.chucky.school.domain")
public class SchoolApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
=======
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);}

>>>>>>> 824df81 (Added unit tests for the student service layer)
}
