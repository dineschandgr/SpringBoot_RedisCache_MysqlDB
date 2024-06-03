package com.dineshchand.cache.springbootredis.web;

import java.util.Optional;

import com.dineshchand.cache.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dineshchand.cache.springbootredis.model.User;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public User save(@RequestBody User user){
        userService.addUser(user);
        return user;
    }

    @GetMapping("/findall")
    public Iterable<User> list(){
        return userService.findAllUsers();
    }

    @GetMapping("/getuser/{id}")
    public User getUser(@PathVariable String id){
        return userService.findUser(id);
    }

    @PutMapping("/update/{id}")
    public User update(@RequestBody User user,@PathVariable String id){
    	System.out.println("inside update");
    	User oldUser = userService.findUser(id);
    	oldUser.setName(user.getName());
    	oldUser.setAge(user.getAge());
        final User updatedUser = userService.updateUser(user);
        return updatedUser;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return id;
    }
}
