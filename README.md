# Goal & About the Project
The goal of this project is to give you hands-on, practical experience with building a RESTful system of microservices using Spring Boot, Spring MVC, Spring Security, Spring Data JPA and Spring Cloud Services.

# Code Coverage and Build Status

![Java CI with Maven](https://github.com/bozicschucky/Course-Attendence-System/actions/workflows/maven-publish.yml/badge.svg?branch=main)

# Course Attendance System
We are trying to create a collection of RESTful web services to automate the process of course attendance taking for Compro students. Imagine we have a badge scanner at every classroom and every day that you go to class, you scan your badge on your way in. The scanner should create an AttendanceRecord for each badge scan that has the student barcode number (could be alphanumeric QR code also), location ID as well as date and time of the scan.

# Requirements
Built into this system, we also know which courses a student is registered for. Each course has many Course Offerings. Each “Course Offering” is an instance of a course offered at a particular time, by a particular faculty (for example CS544-2024-06-V017-PaymanSalek is the June 2024 offering of CS544 by Payman Salek in room V017). Each student can register for many course offerings (one at a time, but many over time) and each course offering has many students on the roster (class list). Each course also has a start and end date and list of “Sessions”. Each Session has a date and also start time and end time.

List of sessions for a course offering could be an in-memory computation or be stored in database (I will leave it to you to decide). Either way standard courses meet every morning (between the start date and end date of the course), Monday through Saturday from 10:00am – 12:30pm and every afternoon from 1:30pm – 3:30pm (you can compute this list based on the start and end date of the course). The last day of the course is always half a day (only morning session).

From the list of sessions for a course and the attendance records, you should be able to create an attendance list. Imagine a GET end point with the following format:
`/course-offerings/CS544-2024-06-01/attendance`
This should return a Microsoft Excel spreadsheet (you can use the Apache POI to convert data into Excel) with columns that represent course sessions (e.g. 2024-06-10-AM) and rows that represent each student in that course offering and then a 1 or zero in each column in front of student representing present or absent.

# Roles
There are multiple roles in the system: student, faculty, staff, sys-admin, etc.

# Use-cases
1. Students should be able to `GET /student-view/course-offerings` and receive the list of courses they are registered in and their grade in that course if the grade is already published.
2. Students should be able to `GET /student-view/course-offerings/{offeringId}/attendance` and receive the list of sessions for that course and their attendance (if the course has started or is in the past)
3. Students should be able to `GET /student-view/attendance-records` and receive all of their attendance records (date and time, location, location type, etc.).
4. Students should be able to `GET /student-view/course-offerings/{offeringId}` and see the course information such as who is teaching it, when it starts, classroom, etc. (minus registration list or course roster). Students should be able to do this for any course; whether they are registered for it or not.
5. Admins (staff, faculty and sys-admins) should be able to `GET /admin-view/courseofferings/{offeringId}/attendance` and download an Excel sheet of the attendance records for the course (This is the most sophisticated use-case in the whole project. It is harder than you think).
6. Admins (staff, faculty and sys-admins) should be able to `GET /admin-view/courseofferings?date=YYYY-MM-DD` and see all the courses that are in session during that date (startDate <= date and endDate >= date).
7. Admins (staff, faculty and sys-admins) should be able to `GET /admin-view/courseofferings/{offeringId}` and see all the course offering with all of its details: start date, end date, faculty who teaches it, list of registrations (class roster), etc.
8. Admins (staff, faculty and sys-admins) should be able to `GET /admin-view/students/{studentID}` and see all the details of one student as well as all the courses the student is registered for (no need to bring back the attendance records list).
9. Sys-Admins should be able to `CRUD /sys-admin/students`
10. Sys-Admins should be able to `CRUD /sys-admin/course-offerings`
11. Sys-Admins should be able to `CRUD /sys-admin/courses`
12. Sys-Admins should be able to `CRUD /sys-admin/locations`