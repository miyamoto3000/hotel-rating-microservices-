package com.shahid.hotelservice.service.impl;

import com.shahid.hotelservice.entities.Hotel;
import com.shahid.hotelservice.exceptions.ResourceNotFoundException;
import com.shahid.hotelservice.repository.HotelRepository;
import com.shahid.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceimpl implements HotelService {

    @Autowired

     private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no hotel found"));
    }

//    @Override
//    public Hotel update(Hotel hotel, String id) {
//
//       Hotel existinguser=hotelRepository.findById(id)
//               .orElseThrow(()->new ResourceNotFoundException("hotel not found "));
//
//       if(hotel.getName()!=null){
//           existinguser.setName(hotel.getName());
//       }
//
//       if(hotel.getLocation()!=null){
//           existinguser.setLocation(hotel.getLocation());
//
//        }
//
//       if(hotel.getAbout()!=null){
//           existinguser.setAbout(hotel.getAbout());
//       }
//
//       return hotelRepository.save(existinguser);
//    }

}
