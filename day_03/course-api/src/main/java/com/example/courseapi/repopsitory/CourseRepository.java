package com.example.courseapi.repopsitory;

import com.example.courseapi.model.Course;
import com.example.courseapi.model.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class CourseRepository {
    private List<Course> courses;
    private final Faker faker;
    private final UserRepository userRepository;

    public CourseRepository(Faker faker, UserRepository userRepository) {
        this.faker = faker;
        this.userRepository = userRepository;

        initCourses();
    }

    private void initCourses() {
        courses = new ArrayList<>();

        Random rd = new Random();
        List<String> topics = List.of("frontend", "backend", "database", "devops", "AWS", "basic", "mobile");
        List<User> users = userRepository.findAll();

        for (int i = 1; i < 21; i++) {
            // Random topic
            List<String> rdTopcis = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                String rdTopic = topics.get(rd.nextInt(topics.size()));
                if(!rdTopcis.contains(rdTopic)) {
                    rdTopcis.add(rdTopic);
                }
            }

            // Random user
            User rdUser = users.get(rd.nextInt(users.size()));

            // Tạo khóa học
            Course course = new Course(
                    i,
                    faker.funnyName().name(),
                    faker.lorem().sentence(20),
                    rd.nextInt(2) == 1 ? "online" : "onlab",
                    rdTopcis,
                    faker.avatar().image(),
                    rdUser.getId()
            );

            courses.add(course);
        }
    }

    public List<Course> findAll() {
        return courses;
    }
}