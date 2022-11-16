package com.example.user.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    Integer id;
    String name;
    String password;
    String email;
    String phone;
    String address;
    String avatar;
}
