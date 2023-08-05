package com.example.demo.service;

 

import java.util.List;

 

import com.example.demo.entity.UserModel;

 

public interface AuthService {
    public Integer saveUser(UserModel user);

    public List<UserModel> getAllUsers();

    public UserModel getUserByEmail(String email);

}