package com.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.app.entities.User;
import com.app.exception.UserExistsException;
import com.app.exception.UserNotFoundException;
import com.app.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/getUsers")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	@PostMapping("/createUser")
	public ResponseEntity<Void> addUser(@Valid @RequestBody User user,UriComponentsBuilder builder) 
	{
		try {
		//return userService.createUser(user);
		userService.createUser(user);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	@GetMapping("users/{id}")
	public Optional<User> getUser(@PathVariable("id")Long id)
	{
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id")Long id,@RequestBody User user)
	{
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id")Long id)
	{
		userService.deleteUserById(id);
	}
	@GetMapping("/users/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username)
	{
		return userService.getByUserName(username);
	}
}
