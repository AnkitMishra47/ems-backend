package com.ems.model;

import com.ems.model.types.Gender;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="ems_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NonNull
    private String name;

    @Column(name="email")
    @NonNull
    private String email;

    @Column(name="mobile")
    @NonNull
    private String mobile;

    @Column(name = "gender", columnDefinition = "VARCHAR(255)")
    private String gender;

    public Employee(@NonNull String name, @NonNull String email, @NonNull String mobile, Gender gender) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender != null ? gender.getValue() : null;
    }

    public Employee(EmployeeDTO employeeDTO){
        this(employeeDTO.getName() , employeeDTO.getEmail() , employeeDTO.getMobile() , employeeDTO.getGender() );
    }

    public Employee(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender != null ? gender.getValue() : null;
    }


}