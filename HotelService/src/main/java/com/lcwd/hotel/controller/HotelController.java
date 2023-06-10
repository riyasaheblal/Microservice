package com.lcwd.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	//create
	//@PreAuthorize(" hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Hotel> createhotel(@RequestBody Hotel hotel){
		Hotel hotel1= hotelService.create(hotel);
		 return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
	}
	
	//get all
	//@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Hotel>> gethotel(){
		List<Hotel> hotel=hotelService.getAll();
		return ResponseEntity.ok(hotel);
		
	}
	
	//get by id
//	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> gethotelid(@PathVariable String id){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(id));
		
	}
	
	//delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		this.hotelService.deleteById(id);
	}
	
	
	//update
	@PutMapping("/{id}")
	public Hotel update(@RequestBody Hotel hotel, @PathVariable String id) {
		this.hotelService.updateById(hotel, id);
		return hotel;
		
	}
}
