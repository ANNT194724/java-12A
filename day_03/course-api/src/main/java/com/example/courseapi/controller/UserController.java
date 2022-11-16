package com.example.courseapi.controller;

import com.example.courseapi.model.Course;
import com.example.courseapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<Course> getCourses(@RequestParam Optional<String> type, @RequestParam Optional<String> topic) {
        return userService.getCourses(type, topic);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Integer id) {
        return userService.getCourse(id);
    }
}
