package com.ems.dto;

import com.ems.model.Employee;
import com.ems.model.User;

public class LoggedInUserDTO  {

    private String name;

    private String username;

    private String email ;

    private String mobile;

    private Employee employee;

    public LoggedInUserDTO(String name, String username, String email, String mobile, Employee employee) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.employee = employee;
    }

    public LoggedInUserDTO() {
    }

    public LoggedInUserDTO(User user ){
        this(user.getName(), user.getUsername() , null ,null , null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Employee getEmployee() {
        return employee;
    }
}
