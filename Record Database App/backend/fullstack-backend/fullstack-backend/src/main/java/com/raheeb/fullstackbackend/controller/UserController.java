package com.raheeb.fullstackbackend.controller;

import com.raheeb.fullstackbackend.exception.UserNotFoundException;
import com.raheeb.fullstackbackend.model.User;
import com.raheeb.fullstackbackend.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRespository userRespository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRespository.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRespository.findAll();
    }
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRespository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRespository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRespository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));

    }
    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Long id ){
        if(!userRespository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRespository.deleteById(id);
        return "user id with "+id+"has been deleted";

    }
}