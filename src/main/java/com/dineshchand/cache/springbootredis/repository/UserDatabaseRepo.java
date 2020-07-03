package com.dineshchand.cache.springbootredis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dineshchand.cache.springbootredis.model.User;

@Repository
public interface UserDatabaseRepo extends JpaRepository<User, String>{

}
