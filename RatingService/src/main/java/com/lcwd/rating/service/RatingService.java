package com.lcwd.rating.service;

import java.util.List;


import com.lcwd.rating.entities.Rating;


public interface RatingService {
	
	
	//create
	Rating create(Rating rating);
	
	//getall ratings
	List<Rating> getRatings();
	
	
	//get by user id
	List<Rating> getRatingsByUserId(String userId);
	
	//get all by hotel id
	List<Rating> getRatingsByHotelId(String hotelId);
	
	//update 
	void deleteById(String ratingId);
	
	//delete
	void updateById(Rating rating,String ratingId);
}
