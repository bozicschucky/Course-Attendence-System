package com.chucky.school.Integration;

import com.chucky.school.repository.StudentRepository;
import com.chucky.school.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class Scheduler {
    @Autowired
    StudentService studentService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    LocalDateTime registrationEnd = LocalDateTime.of(2024,06,20,15, 45,00);
    int minute=0;

    public void scheduleTask(int minute) {
        LocalDateTime now = LocalDateTime.now();
        this.minute = minute;
        long delay = ChronoUnit.MILLIS.between(now, registrationEnd.minusMinutes(minute));

        if (delay > 0) {
            scheduler.schedule(this::printMessage, delay, TimeUnit.MILLISECONDS);
        } else {
            System.out.println("The given time has already passed.");
        }
    }

    private void printMessage(){

        studentService.sendReminderToStudent();
    }
}
