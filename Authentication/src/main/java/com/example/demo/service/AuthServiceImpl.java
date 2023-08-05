package com.example.demo.service;

 

import com.example.demo.entity.UserModel;
import com.example.demo.repository.AuthRepository;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository repository;

    @Override
    public Integer saveUser(UserModel user) {
        return repository.save(user).getUserId();
    }

 

    @Override
    public List<UserModel> getAllUsers() {
        return (List<UserModel>) repository.findAll();
    }

    @Override
    public UserModel getUserByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }
}