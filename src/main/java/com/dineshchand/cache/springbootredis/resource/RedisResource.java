package com.dineshchand.cache.springbootredis.resource;

import java.util.Optional;

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
import com.dineshchand.cache.springbootredis.repository.UserDatabaseRepo;

@RestController
@RequestMapping("/redis")
public class RedisResource {
	
	@Autowired
    private UserDatabaseRepo userRepository;

    @PostMapping("/adduser")
   // @CachePut(value="users",key="#user.id")
    //dont put in cache during add. Otherwise cache will be full
    public User save(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @GetMapping("/findall")
    public Iterable<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/getuser/{id}")
    @Cacheable(value="users",key="#id")
    public Optional<User> getUser(@PathVariable String id){
    	System.out.println("retrieving users from Database*********");
        return userRepository.findById(id);
    }

    @PutMapping("/update/{id}")
    @CachePut(value="users",key="#id")
    public User update(@RequestBody User user,@PathVariable String id){
    	System.out.println("inside update");
    	User oldUser = userRepository.findById(id).orElse(null);
    	oldUser.setName(user.getName());
    	oldUser.setAge(user.getAge());
        final User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value="users",key="#id")
    public String deleteUser(@PathVariable String id){
        userRepository.deleteById(id);
        return id;
    }
}
