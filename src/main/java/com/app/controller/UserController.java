package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.User;
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
	public User addUser(@RequestBody User user) 
	{
		return userService.createUser(user);
	}
	@GetMapping("users/{id}")
	public Optional<User> getUser(@PathVariable("id")Long id)
	{
		return userService.getUserById(id);
	}
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id")Long id,@RequestBody User user)
	{
		return userService.updateUserById(id, user);
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
