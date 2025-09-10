package com.shahid.userservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.userservice.entities.User;
import com.shahid.userservice.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController 
@RequestMapping("/users") 

public class UserController {
     
    @Autowired 
    private UserService userService; 

   @PostMapping 
    public ResponseEntity<User>createUser(@RequestBody User user){
      User savedUser=userService.saveUser(user); 

      return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    } 

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String  userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }   
    }  

     @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    } 

    @DeleteMapping 
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId); 
        return ResponseEntity.noContent().build(); 
    } 

    @PutMapping("/{userId}") 

    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId){
        User updatedUser=userService.updateUser(user, userId); 
        return ResponseEntity.ok(updatedUser); 
    }

}
