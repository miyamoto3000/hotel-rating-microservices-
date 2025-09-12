package com.shahid.userservice.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shahid.userservice.entities.Hotel;
import com.shahid.userservice.entities.Rating;
import com.shahid.userservice.entities.User;
import com.shahid.userservice.exceptions.ResourceNotFoundException;
import com.shahid.userservice.external.HotelService;
import com.shahid.userservice.repository.UserRepository;
import com.shahid.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{
   
    @Autowired 
    private UserRepository userRepository;  

    @Autowired 
    private RestTemplate restTemplate; 

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
   
    @Autowired
    private HotelService hotelService;


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
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found on server !!"+userId));  

      Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList(); 

        List<Rating> ratingsList = ratings.stream().map(rating -> {
             
          //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class); 
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
         
        }).collect(Collectors.toList());
        user.setRatings(ratingsList); 
        logger.info("Response from Rating Service"+ratingsOfUser); 

        
        return user; 

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
