package com.ems.model;

import jakarta.persistence.*;

import java.util.Collection;


@Entity
@Table(name = "ems_sec_user" , uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstname;

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

//    private Collection<Privilege> privileges;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "ems_users_roles",
    joinColumns = @JoinColumn(name = "user_id" ,  referencedColumnName = "id") ,
    inverseJoinColumns = @JoinColumn(name = "role_id" ,  referencedColumnName = "id"))
    private Collection<Role> roles ;


    public User(String firstname, String lastname, String username, String password, Collection<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Collection<Privilege> getPrivileges() {
//        return privileges;
//    }

//    public void setPrivileges(Collection<Privilege> privileges) {
//        this.privileges = privileges;
//    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

//    public User(String firstname, String lastname, String username, String password, Collection<Privilege> privileges, Collection<Role> roles) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.username = username;
//        this.password = password;
//        this.privileges = privileges;
//        this.roles = roles;
//    }



}