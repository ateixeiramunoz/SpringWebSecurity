package com.eoi.springwebsecurity.security.service;


import com.eoi.springwebsecurity.coreapp.dto.UserDto;
import com.eoi.springwebsecurity.coreapp.entities.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    User getByResetPasswordToken(String token);

    void updateResetPasswordToken(String token, String email);

    void updatePassword(User user, String password);
}