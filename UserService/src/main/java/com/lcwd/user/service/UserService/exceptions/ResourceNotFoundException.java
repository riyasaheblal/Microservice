package com.lcwd.user.service.UserService.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	//get method apply get this message
	public ResourceNotFoundException() {
		super("Resource not found!!");
	}
	
	
	//parameteres pass then this message
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
