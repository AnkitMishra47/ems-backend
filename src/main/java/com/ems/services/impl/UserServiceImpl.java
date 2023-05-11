package com.ems.services;

import com.ems.dto.UserRegistrationDTO;
import com.ems.model.Role;
import com.ems.model.User;
import com.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository ;

    @Override
    public User saveUser(UserRegistrationDTO userRegistrationDTO) {
        User user =  new User(userRegistrationDTO.getFirstName() , userRegistrationDTO.getLastName(),
                userRegistrationDTO.getUsername() , userRegistrationDTO.getPassword(),
                Arrays.asList(new Role("canPreview")));

        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
