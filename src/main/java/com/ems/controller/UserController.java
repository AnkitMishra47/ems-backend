package com.ems.controller;


import com.ems.dto.UserRegistrationDTO;
import com.ems.model.User;
import com.ems.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/svc/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String , Object>> registerUserAccount( @RequestBody UserRegistrationDTO userRegistrationDTO){
        return ResponseEntity.ok(userService.saveUser(userRegistrationDTO));
    }


}
