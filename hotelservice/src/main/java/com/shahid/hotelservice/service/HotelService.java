package com.shahid.hotelservice.service;

import com.shahid.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);

//    Hotel update(Hotel hotel,String id);
}
