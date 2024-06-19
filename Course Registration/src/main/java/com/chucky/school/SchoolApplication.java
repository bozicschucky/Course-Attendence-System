package com.chucky.school;

import com.chucky.school.domain.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;


@SpringBootApplication
@EnableKafka
@EnableJpaRepositories(basePackages = "com.chucky.school.repository")
@EntityScan(basePackages = "com.chucky.school.domain")
public class SchoolApplication implements CommandLineRunner {
	@Autowired
	Sender sender;
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		sender.send("topicA", "Hello World");
		System.out.println("Message has been sent");
	}

}
