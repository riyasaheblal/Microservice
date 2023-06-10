package com.lcwd.user.service.UserService.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.UserService.entity.Hotel;
import com.lcwd.user.service.UserService.entity.Rating;
import com.lcwd.user.service.UserService.entity.Users;
import com.lcwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.UserService.repository.UserRepository;
import com.lcwd.user.service.UserService.scternal.services.HotelService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);  
	
	
	//create the users
	@Override
	public Users saveUsers(Users users) {
		//generate unique userid
	String randomUserId	=UUID.randomUUID().toString();
	users.setUserId(randomUserId);
		return userRepository.save(users);
	}

	
	
	//get all the users
	@Override
	public List<Users> getAllUsers() {
		List<Users> findAll = userRepository.findAll();
		//ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/", ArrayList.class);
		//logger.info("{}",ratingOfUser);
		//user.setRatings(ratingOfUser);
		return findAll;
	}

	
	
	//get single user
	@Override
	public Users getById(String userId) {
		//get user from the db with the help of repo

	 Users user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on the server!!:"+ userId));
		
		//fetch rating from the above user from rating service
		//localhost:8083/ratings/users/39fbbb2e-28d9-403c-adbd-4c6bec7e062d
	 //localhost convert to name is done by "load balancer" which we inserted into the MyConfig file part
Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+ user.getUserId() , Rating[].class);
//#this is normal "localhost" 
//	ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/39fbbb2e-28d9-403c-adbd-4c6bec7e062d", ArrayList.class);
	logger.info("{}",ratingOfUser);
	List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
	List<Rating> ratingList = ratings.stream().map(rating ->{
		//api call to hotel service to get the hotel
		//localhost:8082/hotels/f055d040-bf27-4d9d-88c2-ba93296459db
	//	ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);
		//Hotel hotel = forEntity.getBody();
		//feign client
		Hotel hotel= hotelService.getHotel(rating.getHotelId());
		//logger.info("response status code: {}", forEntity.getStatusCode());
		
		//set hotel rating
		rating.setHotel(hotel);
		
		//return rating
		return rating;
	}).collect(Collectors.toList());
	
	
		user.setRatings(ratingList);
		
		return user;
	}



	@Override
	public void deleteById(String userId) {
		
		userRepository.deleteById(userId);
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateById(Users user, String userId) {
		user.setUserId(userId);
		userRepository.save(user);
		// TODO Auto-generated method stub
		
	}

	

}
