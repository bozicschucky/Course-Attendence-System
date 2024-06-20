package com.chucky.school.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
@Aspect
public class TraceAdvice {
    private final LocalDate sessionStartDate = LocalDate.of(2024,6,3);
    private final LocalDate sessionEndDate = LocalDate.of(2024,6,27);

    @Before("execution(* com.chucky.school.Service.AttendanceServiceImpl.createAttendanceRecord(..))")
    public void tracebeforemethod(JoinPoint joinpoint) throws IllegalAccessException {
        //Object[] args = joinpoint.getArgs();

        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        LocalTime currentTime = now.toLocalTime();

        if (!today.isBefore(sessionStartDate) &&!today.isAfter(sessionEndDate)) {
            DayOfWeek dayOfWeek = today.getDayOfWeek();
            if ( dayOfWeek != DayOfWeek.SUNDAY){
                if (currentTime.isAfter(LocalTime.of(7,0)) && currentTime.isBefore(LocalTime.of(11,00)) ||
                currentTime.isAfter(LocalTime.of(13,30)) && currentTime.isBefore(LocalTime.of(15,30))
                ){

                    if (!(today.equals(sessionEndDate) && currentTime.isAfter(LocalTime.of(12,30)))){
                        return;
                    }
                }
            }
        }
            throw new IllegalAccessException("Session is closed");

    }



}
