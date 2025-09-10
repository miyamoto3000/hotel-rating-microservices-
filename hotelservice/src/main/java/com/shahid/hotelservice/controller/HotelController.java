package com.shahid.hotelservice.controller;

import com.shahid.hotelservice.entities.Hotel;
import com.shahid.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel>getHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    @GetMapping

    public ResponseEntity<List<Hotel>>getAll(){
       return ResponseEntity.ok(hotelService.getAll());
    }

//    @PutMapping({"/hotelId"})
//
//    public ResponseEntity<Hotel>updateHotel(Hotel hotel,@PathVariable String hotelId){
//
//        Hotel updatedHotel=hotelService.update(hotel,hotelId);
//
//        return ResponseEntity.ok(updatedHotel);
//    }




}
