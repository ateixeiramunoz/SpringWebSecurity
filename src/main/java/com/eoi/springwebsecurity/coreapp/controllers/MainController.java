package com.eoi.springwebsecurity.coreapp.controllers;

import com.eoi.springwebsecurity.coreapp.dto.UserDto;
import com.eoi.springwebsecurity.coreapp.services.DefaultService;
import com.eoi.springwebsecurity.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.extras.springsecurity6.auth.Authorization;


import java.security.Principal;
import java.util.List;

/**
 * The type Main controller.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultService defaultService;


    /**
     * Index string.
     *
     * @param principal the principal
     * @return the string
     */
    @GetMapping("/")
    String index(Principal principal) {
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    /**
     * Show admin page string.
     *
     * @return the string
     */
    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    /**
     * Show user page string.
     *
     * @return the string
     */
    @GetMapping("/user")
    public String showUserPage() {
        return "user";
    }

    /**
     * Users string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * Chat string.
     *
     * @return the string
     */
    @GetMapping("/chat")
    public String chat(Principal principal, Model model){

        String userID = principal.getName();
        model.addAttribute("userID", userID);

        return "chat";
    }


    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") Integer id, Principal principal, Model model){

        String userID = principal.getName();
        model.addAttribute("user", userService.findUserByEmail(userID));
        model.addAttribute("userID", userID);
        return "test";
    }


    @GetMapping("/security")
    public String test(Principal principal, Authorization authorization,Model model){
        model.addAttribute("principal", principal);
        model.addAttribute("authorization", authorization);
        return "security";
    }




}
