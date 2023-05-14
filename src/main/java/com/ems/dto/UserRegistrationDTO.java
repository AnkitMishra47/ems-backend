package com.ems.dto;

import com.ems.model.types.Gender;

public class UserRegistrationDTO {

    private String name;

    private String firstName ;

    private String lastName;

    private String password;

    private String username;
    private Gender gender ;

    public UserRegistrationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRegistrationDTO(String name, String firstName, String lastName, String password, String username) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

    public boolean checkAllFieldPresent(){
        return this.checkLoginFieldsPresent() && this.firstName != null && this.lastName != null;
    }

    public boolean checkLoginFieldsPresent(){
        return this.username != null && this.password != null;
    }
}
