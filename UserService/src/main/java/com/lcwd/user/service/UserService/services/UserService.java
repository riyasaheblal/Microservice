package com.lcwd.user.service.UserService.services;

import java.util.List;

import com.lcwd.user.service.UserService.entity.Users;

public interface UserService {
	
	//user operations
	
	//create
	Users saveUsers(Users users);
	
	//get all users
	List<Users> getAllUsers();
	
	//get user by id
	Users getById(String userId);
	
	//delete user by id
	void  deleteById(String userId);
	
	//update by id
	void updateById(Users user,String userId);

}
