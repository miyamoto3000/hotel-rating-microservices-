package com.shahid.userservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shahid.userservice.entities.User;
import com.shahid.userservice.exceptions.ResourceNotFoundException;
import com.shahid.userservice.repository.UserRepository;
import com.shahid.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{
   
    @Autowired 
    private UserRepository userRepository; 

    @Override
    public User saveUser(User user) {  
        
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() { 
         return userRepository.findAll(); 
    }
       

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found on server !!"+userId));
    }

    @Override
    public void deleteUser(String userId) {
         
        userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found on server !!"+userId)); 
        userRepository.deleteById(userId); 

    }

    @Override
    public User updateUser(User user, String userId) {
           
        User existingUser=userRepository.findById(userId)
        .orElseThrow(()-> new RuntimeException("User with given id not found on server !!"+userId)); 
        
        if(user.getName()!=null){
            existingUser.setName(user.getName());
        } 
        if(user.getEmail()!=null){
            existingUser.setEmail(user.getEmail());
        } 

        if(user.getAbout()!=null){
            existingUser.setAbout(user.getAbout());
        } 

        return userRepository.save(existingUser);
    }
    
}
