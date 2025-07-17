package com.example.prak.repository.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


@Table(name="users")
public class User implements Serializable {
    @Id
    private Long id;
    private String username;
    private String firstname;
    private String email;
    private String password;
    private boolean confirmed;
    private String token;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isConfirmed() {
        return confirmed;
    }
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    @Override
    public String toString() {
            return "User [id=" + id + ", email=" + email + ", username=" + username + ", firstname=" + firstname +  ", password=" + password;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User)o;
        if (user.hashCode() == this.hashCode())
            return true;
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
