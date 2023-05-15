package com.ems.services.impl;

import com.ems.dto.UserRegistrationDTO;
import com.ems.model.Role;
import com.ems.model.User;
import com.ems.repository.RoleRepository;
import com.ems.repository.UserRepository;
import com.ems.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Map<String , Object> saveUser(UserRegistrationDTO userRegistrationDTO , String roleName) {
        String              username                = userRegistrationDTO.getUsername();
        boolean             isUsernameAlreadyExist  = userRepository.findOneByUsername(username) != null;
        Map<String, Object> response                = new HashMap<>();

        if (!isUsernameAlreadyExist)
        {
            User user =  new User(
                    userRegistrationDTO.getFirstName() ,
                    userRegistrationDTO.getLastName(),
                    userRegistrationDTO.getUsername() ,
                    passwordEncoder.encode(userRegistrationDTO.getPassword()));

            addRoleToUser(user , roleName);

            response.put("success" , true);
        }
        else
        {
            response.put("success" , false);
            response.put("message" , "username already exist");
        }

        return response;
    }

    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.findOneByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findOneByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword() , mapRolesToAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        if (roles != null){
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        }

        return null ;
    }

    public void addRoleToUser(User user, String roleName) {
        Role role = roleRepository.findRoleByName(roleName);
        if (user != null && role != null) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
}
