package com.dineshchand.cache.springbootredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dineshchand.cache.springbootredis.model.User;
import com.dineshchand.cache.springbootredis.repository.UserDatabaseRepo;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserDatabaseRepo userDatabaseRepo;

	// @CachePut(value="users",key="#user.id")
	//dont put in cache during add. Otherwise cache will be full
	public User addUser(User user) {
		userDatabaseRepo.save(user);
		return user;
	}

	public Iterable<User> findAllUsers() {
		return userDatabaseRepo.findAll();
	}

	@CacheEvict(value="users",key="#id")
	public void deleteUser(String id) {
		userDatabaseRepo.deleteById(id);;
	}

	@CachePut(value="users",key="#id")
	public User updateUser(User user) {
		return userDatabaseRepo.save(user);
	}

	@Cacheable(value="users",key="#id")
	public User findUser(String id) {
		Optional<User> maybeUser =  userDatabaseRepo.findById(id);
		return maybeUser.orElse(null);
	}
	
}
