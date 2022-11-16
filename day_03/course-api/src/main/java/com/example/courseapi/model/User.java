package com.example.courseapi.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    Integer id;
    String name;
    String email;
    String phone;
    String avatar;
}
