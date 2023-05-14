package com.ems.controller;


import com.ems.dto.UserRegistrationDTO;
import com.ems.jwt.AuthenticationResponse;
import com.ems.jwt.JwtAuthTokenFilter;
import com.ems.jwt.JwtUtils;
import com.ems.services.UserService;
import com.ems.utils.EMSUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/svc/api/")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    @Qualifier("userServiceImpl")
    private  UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);



    @PostMapping("/register")
    public ResponseEntity<Map<String , Object>> registerUserAccount( @RequestBody UserRegistrationDTO userRegistrationDTO){
        Map response = new HashMap<>();

        if (!userRegistrationDTO.checkAllFieldPresent()){
            response.put("error" , EMSUtils.mandatoryValidation());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(userService.saveUser(userRegistrationDTO , EMSUtils.EMPLOYEE_ROLE));
    }

    public Authentication getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(HttpServletRequest request, @RequestBody UserRegistrationDTO userDTO) {
        try {

            HashMap response = new HashMap();
            response.put("error" , EMSUtils.mandatoryValidation());

            if (!userDTO.checkLoginFieldsPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid Username and Password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(userDTO.getUsername());

        final String jwt = jwtUtils.generateToken(userDetails);

        HashMap map = new HashMap();
        map.put("token" , jwt);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/EnvironmentInformation")
    public ResponseEntity<?> getEnvironmentInformation(){
        logger.info("Environment called");

        if (getUser() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session Expired");
        }

        Map map = new HashMap();
        map.put("result", "ok");
        return ResponseEntity.ok(map);
    }
}
