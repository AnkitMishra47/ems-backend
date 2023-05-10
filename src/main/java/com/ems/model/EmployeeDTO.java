package com.ems.model;

import com.ems.model.types.Gender;

public class EmployeeDTO {

    private String name ;
    private Long id ;
    private String email;
    private String mobile;

    private Gender gender ;

    public EmployeeDTO(String name, Long id, String email, String mobile, Gender gender) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
    }

    public EmployeeDTO(String name, String email, String mobile, Gender gender) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public EmployeeDTO(Employee employee){
        this(employee.getName() , employee.getId(), employee.getEmail() , employee.getMobile() , Gender.fromValue(employee.getGender()));
    }

    public EmployeeDTO() {
    }
}
