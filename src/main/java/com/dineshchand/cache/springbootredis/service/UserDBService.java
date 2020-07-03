package com.dineshchand.cache.springbootredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dineshchand.cache.springbootredis.model.User;
import com.dineshchand.cache.springbootredis.repository.UserDatabaseRepo;

@Service
public class UserDBService {

	@Autowired
	UserDatabaseRepo userDatabaseRepo;
	
	public User addUser(User user) {
		userDatabaseRepo.save(user);
		return user;
	}

	public Iterable<User> findAllUsers() {
		return userDatabaseRepo.findAll();
	}

	public void deleteUser(String id) {
		userDatabaseRepo.deleteById(id);;
	}

	public User updateUser(User user) {
		return userDatabaseRepo.save(user);
	}

	@Cacheable(value="users",key="#userId")
	public User findUser(String id) {
		
		return userDatabaseRepo.findById(id).orElse(new User("dummy","dummy",100));
	}
	
}
