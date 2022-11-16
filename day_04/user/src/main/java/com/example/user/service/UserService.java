package com.example.user.service;

import com.example.user.dto.*;
import com.example.user.exception.BadRequestException;
import com.example.user.exception.NotFoundException;
import com.example.user.model.User;
import com.example.user.repopsitory.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto convertUserToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getAvatar()
        );
    }
    
    public User checkPresent(Integer id) {
        Optional<User> user = userRepository.findAll().stream()
                .filter(u -> u.getId().equals(id)).findAny();
        if(user.isEmpty()) {
            throw new NotFoundException("User with id = " + id + " doesn't exist");
        }
        return user.get();
    }

    public PagedResponse<UserDto> getUsers(Integer page, Integer limit) {
        Integer totalPage = (int) Math.ceil((float)userRepository.findAll().size()/limit);
        Pageable pageable = PageRequest.of(page - 1, limit);
        int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), userRepository.findAll().size());
        Page<User> users = new PageImpl<>(userRepository.findAll().subList(start, end), pageable, userRepository.findAll().size());
        List<UserDto> results = new ArrayList<>();
        users.forEach(user -> results.add(convertUserToDto(user)));
        return new PagedResponse<>(page, limit, totalPage, results);
    }

    public List<UserDto> searchUsers(String name) {
        List<User> users = userRepository.findAll().stream()
                .filter(user -> user.getName().contains(name)).toList();
        List<UserDto> results = new ArrayList<>();
        users.forEach(user -> results.add(convertUserToDto(user)));
        return results;
    }

    public UserDto getUser(Integer id) {
        User user = checkPresent(id);
        return convertUserToDto(user);
    }

    public UserDto createUser(AddUserRequest request) {
        Integer id = userRepository.findAll().stream().max(Comparator.comparing(User::getId)).get().getId() + 1;
        User user = new User(id, request.getName(), request.getPassword(), request.getEmail(), request.getPhone(), request.getAddress(), null);
        userRepository.findAll().add(user);
        return convertUserToDto(user);
    }

    public UserDto updateUser(UpdateUserRequest request, Integer id) {
        User user = checkPresent(id);
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        return convertUserToDto(user);
    }

    public void deleteUser(Integer id) {
        User user = checkPresent(id);
        userRepository.findAll().remove(user);
    }

    public void updateAvatar(ChangeAvatarRequest request, Integer id) {
        User user = checkPresent(id);
        user.setAvatar(request.getAvatar());
    }

    public void changePassword(ChangePasswordRequest request, Integer id) {
        User user = checkPresent(id);
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("Wrong password");
        }
        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("New password can not be the same");
        }
        user.setPassword(request.getNewPassword());
    }

    public String forgotPassword(Integer id) {
        User user = checkPresent(id);
        Faker faker = new Faker();
        String newPassword = faker.regexify("[a-z0-9]{8}");
        user.setPassword(newPassword);
        return "Your new password: " + newPassword;
    }
}
