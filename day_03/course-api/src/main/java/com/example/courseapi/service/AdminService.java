package com.example.courseapi.service;

import com.example.courseapi.exception.NotFoundException;
import com.example.courseapi.model.Course;
import com.example.courseapi.repopsitory.CourseRepository;
import com.example.courseapi.repopsitory.UserRepository;
import com.example.courseapi.request.UpsertCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    public Course createCourse(UpsertCourseRequest request) {
        Integer id = courseRepository.findAll().stream().max(Comparator.comparing(Course::getId)).get().getId() + 1;
        Course course = new Course(id, request.getName(), request.getDescription(), request.getType(), request.getTopics(), request.getThumbnail(), request.getUserId());
        courseRepository.findAll().add(course);
        return course;
    }

    public Course updateCourse(UpsertCourseRequest request, Integer id) {
        Optional<Course> courseOptional = courseRepository.findAll().stream()
                .filter(c -> c.getId().equals(id)).findAny();
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("Can not find course with id " + id);
        }
        Course course = courseOptional.get();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setType(request.getType());
        course.setTopics(request.getTopics());
        course.setThumbnail(request.getThumbnail());
        course.setUserId(request.getUserId());
        return course;
    }

    public void deleteCourse(Integer id) {
        Optional<Course> course = courseRepository.findAll().stream()
                .filter(c -> c.getId().equals(id)).findAny();
        if (course.isEmpty()) {
            throw new NotFoundException("Can not find course with id " + id);
        }
        courseRepository.findAll().remove(course.get());
    }
}
