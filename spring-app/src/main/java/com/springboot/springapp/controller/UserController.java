package com.springboot.springapp.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springapp.repository.UserRepository;
import com.springboot.springapp.entity.UserModel;
import com.springboot.springapp.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController	
@RequestMapping("/api/user")

public class UserController {
	
	@Autowired	
	//injected
	private UserRepository userRepoitory;
	
	
	//get all user
	@GetMapping	
	       public List<UserModel> getAllUsers(){
		return this.userRepoitory.findAll();
	       }
	//get user by ID
	@GetMapping("/{id}")
	public UserModel getUserById (@PathVariable(value="id") long userId) {
		return this.userRepoitory.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
	}
	//Create User
	@PostMapping
	public UserModel createUser (@RequestBody UserModel user) {
		return this.userRepoitory.save(user);
	}
	
	
	//Update User
	@PutMapping("/{id}")
	public UserModel updateUser (@RequestBody UserModel user, @PathVariable ("id") long userId) {
		UserModel existingUser = this.userRepoitory.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepoitory.save(existingUser);
		
		
	}
	
	
	//Delete user by Id
	@DeleteMapping("/{id}")
	
	public ResponseEntity<UserModel> deleteUser(@PathVariable ("id") long userId){
		UserModel existingUser = this.userRepoitory.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));
		this.userRepoitory.delete(existingUser);
		return ResponseEntity.ok().build();	
		}
	
	
	
}
