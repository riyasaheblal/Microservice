package com.lcwd.rating.controller;

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

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	//create
//	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		Rating rating2=ratingService.create(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(rating2);
		
	}
	
	//get all
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		List<Rating> rating=ratingService.getRatings();
		return ResponseEntity.ok(rating);
	}
	//get all
	//get user id
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getUserId(@PathVariable String userId){
		List<Rating> rating=ratingService.getRatingsByUserId(userId);
		return ResponseEntity.ok(rating);
	}
	//get hotel id
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getHotelId(@PathVariable String hotelId){
		List<Rating> rating=ratingService.getRatingsByHotelId(hotelId);
		return ResponseEntity.ok(rating);
	}
	
	@DeleteMapping("/{ratingId}")
	public void delete(@PathVariable("ratingId") String ratingId) {
		this.ratingService.deleteById(ratingId);
	}

	
	@PutMapping("/{ratingId}")
	public Rating update(@RequestBody Rating rating, String ratingId)
	{
		this.ratingService.updateById(rating, ratingId);
		return rating;
		
	}
	
}