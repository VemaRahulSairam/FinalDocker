package com.example.demo.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 

import com.example.demo.entity.UserModel;

 

@Repository
public interface AuthRepository extends JpaRepository<UserModel,Integer> {

	@Query("select u from UserModel u where u.email=:email and u.password =:password")
     public  UserModel findByEmailAndPassword(@Param("email")String email, @Param("password")  String password);

	
}