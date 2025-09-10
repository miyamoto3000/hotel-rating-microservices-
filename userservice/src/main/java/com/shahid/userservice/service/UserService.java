package com.shahid.userservice.service;

import java.util.List;

import com.shahid.userservice.entities.User;

public interface UserService {
    User saveUser(User user); 

    //get all users 
     
    List<User>getAllUsers();
   
    //get single user of given id
    User getUser(String userId);  



    void deleteUser(String userId);  


    User updateUser(User user, String userId); 

    
} 