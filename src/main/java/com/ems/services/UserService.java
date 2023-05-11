package com.ems.services;

import com.ems.dto.UserRegistrationDTO;
import com.ems.model.User;

public interface UserService {

    public User saveUser(UserRegistrationDTO userRegistrationDTO);

    public User getUserByUsername(String username);
}
