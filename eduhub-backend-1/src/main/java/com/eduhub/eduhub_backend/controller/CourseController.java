package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.component.CourseService;
import com.eduhub.eduhub_backend.component.StudentService;

import com.eduhub.eduhub_backend.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    CourseService courseService;

    @Autowired
    StudentService studentService;

    List<Course> courseList = new ArrayList<>();


    public CourseController(CourseService courseService) {

        this.courseService = courseService;

        courseList.add(new Course("EE101", "Java", 4));
        courseList.add(new Course("EE102", "M&I", 3));
        courseList.add(new Course("EE103", "DBMS", 4));
        courseList.add(new Course("EE104", "ADT", 5));
        courseList.add(new Course("EE105", "MPMC", 3));
        courseList.add(new Course("EE106", "OOPS", 3));
    }

    @GetMapping("get-course")
    public String getCourse() {

        return courseService.getCourse();
    }


    @GetMapping("get-component")
    public String getComponent() {

        return studentService.getComponent();
    }


    @GetMapping("courses")
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(courseList);
    }
    @GetMapping("courses/{courseCode}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String courseCode) {

        Course course = courseList.stream().filter(c -> c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseCode", courseCode));
                 return ResponseEntity.ok(course);
    }

//    GET /course?courseCode=EE999
    @GetMapping("course")
    public ResponseEntity<Course> getCourseRequestParam(@RequestParam String courseCode) {

        Course course = courseList.stream().filter(c -> c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseCode", courseCode));
                return ResponseEntity.ok(course);
    }

    @PostMapping("create-course")
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course) {

        courseList.add(course);

        return ResponseEntity.ok(course);
    }


    @PutMapping("update-course/{courseCode}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable String courseCode,
            @RequestBody Course updatedCourse) {

        Course course = courseList.stream()
                .filter(c -> c.getCourseCode()
                        .equalsIgnoreCase(courseCode))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course",
                                "courseCode",
                                courseCode));

        course.setSubjectName(updatedCourse.getSubjectName());
        course.setCredits(updatedCourse.getCredits());

        return ResponseEntity.ok(course);
    }
    @DeleteMapping("delete-course/{courseCode}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseCode) {

        Course course = courseList.stream().filter(c -> c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseCode", courseCode));

        courseList.remove(course);

        return ResponseEntity.ok("Course Deleted Successfully");
    }

//    http://localhost:8080/courses/query/*
    @PutMapping("/query/{courseCode}")
    public String queryCourse(@PathVariable String courseCode) throws Exception {
        if(courseCode.startsWith("*")){
            throw new IllegalArgumentException("It is having a special character");
        }
        else if(courseCode.startsWith("6")){
            throw new Exception();
        }
        return courseCode;
    }


}