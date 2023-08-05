package com.example.demo.entity;

 


 

public class UserModel {

     
      private Integer userId;

    
      private String name;


    
      private String email;

     
      private String password;

   
      private String status;

      public UserModel(Integer userId, String name, String email, String password, String status, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.role = role;
	}



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

 