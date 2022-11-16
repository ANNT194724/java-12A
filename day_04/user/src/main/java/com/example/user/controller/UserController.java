package com.example.user.controller;

import com.example.user.dto.*;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public PagedResponse<UserDto> getUsers(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return userService.getUsers(page, limit);
    }

    @GetMapping("/search")
    public List<UserDto> searchUser(@RequestParam String name) {
        return userService.searchUsers(name);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDto addUser(@RequestBody AddUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UpdateUserRequest request, @PathVariable Integer id) {
        return userService.updateUser(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}/update-avatar")
    public void updateAvatar(@RequestBody ChangeAvatarRequest request, @PathVariable Integer id) {
        userService.updateAvatar(request, id);
    }

    @PutMapping("/{id}/update-password")
    public void changePassword(@RequestBody ChangePasswordRequest request, @PathVariable Integer id) {
        userService.changePassword(request,id);
    }

    @PostMapping("/{id}/forgot-password")
    public String forgotPassword(@PathVariable Integer id) {
        return userService.forgotPassword(id);
    }
}
