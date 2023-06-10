package com.lcwd.rating.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) {
		//generate unique rating
		//String randomRatingId	=UUID.randomUUID().toString();
		//rating.setUserId(randomRatingId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public void deleteById(String ratingId) {
		this.ratingRepository.deleteById(ratingId);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateById(Rating rating, String ratingId) {
		rating.setRatingId(ratingId);
		this.ratingRepository.save(rating);
		// TODO Auto-generated method stub
		
	}

	
	
}
