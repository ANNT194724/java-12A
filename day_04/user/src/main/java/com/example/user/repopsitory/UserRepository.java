package com.example.user.repopsitory;

import com.example.user.model.User;
import com.github.javafaker.Faker;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class UserRepository {
    private List<User> users;
    private final Faker faker;

    private UserRepository(Faker faker) {
        this.faker = faker;
        initUsers();
    }

    private void initUsers() {
        users = new ArrayList<>();
        for (int i = 1; i < 40; i++) {
            User user = new User(
                    i,
                    faker.name().fullName(),
                    faker.regexify("[a-z0-9]{8}"),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().phoneNumber(),
                    faker.address().cityName(),
                    faker.avatar().image());
            users.add(user);
        }
    }

    public List<User> findAll() {
        return users;
    }
}
