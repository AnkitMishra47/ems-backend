package com.ems.services;

import com.ems.dto.UserRegistrationDTO;
import com.ems.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Map;

public interface UserService extends UserDetailsService
{
    public Map<String, Object> saveUser(UserRegistrationDTO userRegistrationDTO, String roleName);

    public boolean validateUser(String username, String password);

    public User findByUsername(String username);
}
