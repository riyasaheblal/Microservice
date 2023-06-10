package com.lcwd.hotel.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.repository.HotelRepository;
import com.lcwd.hotel.services.exceptions.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	
	//create hotel
	@Override
	public Hotel create(Hotel hotel) {
		String randomUserId	=UUID.randomUUID().toString();
		hotel.setId(randomUserId);
		return hotelRepository.save(hotel);
	}
	
	
	//getall data of hotel

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	
	
	//get data by id
	@Override
	public Hotel get(String id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on the server!!:"+ id));
	
	//return hotelRepository.findById(id).orElseThrow();
	}


	@Override
	public void updateById(Hotel hotel, String id) {
		hotel.setId(id);
		this.hotelRepository.save(hotel);
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteById(String id) {
		this.hotelRepository.deleteById(id);
		// TODO Auto-generated method stub
		
	}

	
	
}
