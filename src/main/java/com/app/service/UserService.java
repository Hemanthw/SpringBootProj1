package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.User;
import com.app.exception.UserExistsException;
import com.app.exception.UserNotFoundException;
import com.app.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User>getAllUsers()
	{
		return userRepository.findAll(); 
	}
	public User createUser(User user)throws UserExistsException
	{
		User existingUser=userRepository.findByUsername(user.getUsername());
		if(existingUser!=null)
		{
			throw new UserExistsException("User Already exists in Repository");
		}
		return userRepository.save(user);
	}
	public Optional<User> getUserById(Long id)throws UserNotFoundException
	{
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) 
		{
			throw new UserNotFoundException("User Not Found in User Repository");
		}
		return user;
	}
	public User updateUserById(Long id,User user)throws UserNotFoundException
	{
		Optional<User> Optionaluser=userRepository.findById(id);
		if(!Optionaluser.isPresent()) 
		{
			throw new UserNotFoundException("User Not Found in User Repository,provid correct User Id to Update");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	public void deleteUserById(Long id)
	{
		if(userRepository.findById(id).isPresent())
		{
			userRepository.deleteById(id);
		}
	}
	public User getByUserName(String username)
	{
		return userRepository.findByUsername(username);
	}
}
