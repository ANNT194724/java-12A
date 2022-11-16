package com.example.courseapi.service;

import com.example.courseapi.exception.NotFoundException;
import com.example.courseapi.model.Course;
import com.example.courseapi.repopsitory.CourseRepository;
import com.example.courseapi.repopsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getCourses(Optional<String> type, Optional<String> topic) {
        List<Course> results = courseRepository.findAll();
        if(type.isPresent()) {
            results = results.stream()
                    .filter(course -> course.getType().equals(type.get()))
                    .collect(Collectors.toList());
        }
        if (topic.isPresent()){
            results = results.stream()
                    .filter(course -> course.getTopics().contains(topic.get()))
                    .collect(Collectors.toList());
        }
        return results;
    }

    public Course getCourse(Integer id) {
        Optional<Course> course = courseRepository.findAll().stream()
                .filter(c -> c.getId().equals(id)).findAny();
        if (course.isEmpty()) {
            throw new NotFoundException("Can not find course with id " + id);
        }
        return course.get();
    }
}
