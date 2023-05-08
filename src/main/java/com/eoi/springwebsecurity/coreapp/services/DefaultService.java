package com.eoi.springwebsecurity.coreapp.services;

import com.eoi.springwebsecurity.coreapp.entities.User;
import com.eoi.springwebsecurity.coreapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class DefaultService {

    @Autowired
    UserRepository userRepository;


    public String getUserIdFromCalendarioID(Integer i)
    {

        Optional<User> user = userRepository.findById(Long.valueOf(i));

        return user.get().getEmail();
    }

}
