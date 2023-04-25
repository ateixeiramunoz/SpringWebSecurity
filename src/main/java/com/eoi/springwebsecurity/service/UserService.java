package com.eoi.springwebsecurity.service;


import com.eoi.springwebsecurity.dto.UserDto;
import com.eoi.springwebsecurity.entities.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}