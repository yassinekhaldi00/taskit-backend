package com.khaldi.taskit.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.khaldi.taskit.model.User;
import com.khaldi.taskit.service.UserService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("/auth")
	public User getUser(@RequestBody ObjectNode objectNode) {
		String email = objectNode.get("email").asText();
		String password = objectNode.get("password").asText();
		return userService.getUser(email, password);
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable("id") long id) {
		return userService.deleteUser(id);
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	

}
