package com.example.courseapi.controller;

import com.example.courseapi.model.Course;
import com.example.courseapi.request.UpsertCourseRequest;
import com.example.courseapi.service.AdminService;
import com.example.courseapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apip/v1/admin/courses")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @GetMapping
    public List<Course> getCourses(@RequestParam Optional<String> type, @RequestParam Optional<String> topic) {
        return userService.getCourses(type, topic);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Integer id) {
        return userService.getCourse(id);
    }

    @PostMapping
    public Course addCourse(@RequestBody @Valid UpsertCourseRequest request) {
        return adminService.createCourse(request);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@RequestBody @Valid UpsertCourseRequest request, @PathVariable Integer id) {
        return adminService.updateCourse(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        adminService.deleteCourse(id);
    }
}
