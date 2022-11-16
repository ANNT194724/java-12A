package com.example.courseapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Course {
    Integer id;
    String name;
    String description;
    String type;
    List<String> topics;
    String thumbnail;
    Integer userId;
}
