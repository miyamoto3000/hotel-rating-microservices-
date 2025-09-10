package com.shahid.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shahid.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

    
} 
