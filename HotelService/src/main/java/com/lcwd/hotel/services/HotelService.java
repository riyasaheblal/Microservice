package com.lcwd.hotel.services;

import java.util.List;

import com.lcwd.hotel.entity.Hotel;

public interface HotelService {

	
	//create
	Hotel create(Hotel hotel);
	
	//get all
	List<Hotel> getAll();
	
	//get by id
	Hotel get(String id);
	
	//update
	void updateById(Hotel hotel,String id);
	
	//delete
	void deleteById(String id);
}
