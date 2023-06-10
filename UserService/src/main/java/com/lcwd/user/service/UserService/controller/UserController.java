package com.lcwd.user.service.UserService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.UserService.entity.Users;
import com.lcwd.user.service.UserService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);  

	
	//create
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users users){
		Users user=userService.saveUsers(users);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	
	//get all users
	@GetMapping
	public ResponseEntity<List<Users>> getAllUser(){
		
		return ResponseEntity.ok(userService.getAllUsers());
		}
	
//	
//	//get user by id

//	int retryCount;
	
 @GetMapping("/{userId}")
	 // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//  @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	   @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	   public ResponseEntity<Users> getSingleUser(@PathVariable String userId) {
	     //  logger.info("Get Single User Handler: UserController");
  //  logger.info("Retry count: {}", retryCount);
	//       retryCount++;
	       Users user = userService.getById(userId);
	       return ResponseEntity.ok(user);
	   }

 //creating fall back  method for circuitbreaker


   public ResponseEntity<Users> ratingHotelFallback(String userId, Exception ex) {
     logger.info("Fallback is executed because service is down : ", ex.getMessage());

      ex.printStackTrace();

       Users user = Users.builder()
    		   .email("dummy@gmail.com")
    		   .name("Dummy")
    		   .about("This user is created dummy because some service is down")
    		   .userId("141234").build();
       return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
   }


//	private ResponseEntity<Users> ResponseEntity(UsersBuilder user, HttpStatus ok) {
//		// TODO Auto-generated method stub
//		return null;
//	}
   
   @DeleteMapping("/{userId}")
   public void delete(@PathVariable("userId") String userId) {
	   this.userService.deleteById(userId);
   }
   
   @PutMapping("/{userId}")
   public Users update(@RequestBody Users user, @PathVariable("userId") String userId) {
	
	   this.userService.updateById(user, userId);
	   return user;
	   
   }
}
