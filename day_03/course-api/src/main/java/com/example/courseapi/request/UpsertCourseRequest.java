package com.example.courseapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpsertCourseRequest {
    @NotBlank(message = "Name can not be blank!")
    String name;
    @NotBlank(message = "Description can not be blank")
    @Size(min = 50, message = "Description must be at least 50 characters long!")
    String description;
    @NotBlank(message = "Type can not be blank!")
    String type;
    List<String> topics;
    String thumbnail;
    Integer userId;
}
