package com.example.demo.entity;

 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

 

@Entity
@Table(name = "auth_users")
public class UserModel {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "user_id")
      private Integer userId;

      @Column(name="name")
      private String name;


      @Column(unique = true)
      private String email;

      @Column(name = "password")
      private String password;

      @Column (name= "Status")
      private String status;

      public String getStatus() {
        return status;
    }

 

    public void setStatus(String status) {
        this.status = status;
    }

 

    public String getName() {
        return name;
    }

 

    public void setName(String name) {
        this.name = name;
    }

 

    public String getRole() {
        return role;
    }

 

    public void setRole(String role) {
        this.role = role;
    }

 

    @Column(name="ROLE")
      private String role;

 

   
    public Integer getUserId() {
        return userId;
    }

 

    public void setUserId(Integer userId) {
        this.userId = userId;
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

 

   
}

 