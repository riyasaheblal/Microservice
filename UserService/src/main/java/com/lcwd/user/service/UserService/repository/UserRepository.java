package com.lcwd.user.service.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.user.service.UserService.entity.Users;

public interface UserRepository extends JpaRepository<Users, String>{

	

}
