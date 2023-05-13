package com.ems.services.impl;

import com.ems.dto.UserRegistrationDTO;
import com.ems.model.Role;
import com.ems.model.User;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Map<String , Object> saveUser(UserRegistrationDTO userRegistrationDTO) {
        String              username                = userRegistrationDTO.getUsername();
        boolean             isUsernameAlreadyExist  = userRepository.findOneByUsername(username) != null;
        Map<String, Object> response                = new HashMap<>();

        if (!isUsernameAlreadyExist)
        {
            User user =  new User(
                    userRegistrationDTO.getFirstName() ,
                    userRegistrationDTO.getLastName(),
                    userRegistrationDTO.getUsername() ,
                    passwordEncoder.encode(userRegistrationDTO.getPassword()),
                    Arrays.asList(new Role("EMPLOYEE_USER")));

            userRepository.save(user);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findOneByUsername(username);

        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username and password");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword() , mapRolesToAuthorities(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
