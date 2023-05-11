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
public class UserRegistrationController {

    @Autowired
    private UserService userService ;

    @PostMapping("/register")
    public ResponseEntity<Map<String , Object>> registerUserAccount( @RequestBody UserRegistrationDTO userRegistrationDTO){
        User user = userService.saveUser(userRegistrationDTO);
        Map<String , Object> response = new HashMap<>();
        response.put("message" , "User Registered Successfully");
        response.put("ObjectID" , user.getId());

        return ResponseEntity.ok(response);
    }


}
