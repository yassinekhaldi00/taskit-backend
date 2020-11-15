package com.khaldi.taskit.dao;

import java.util.List;

import com.khaldi.taskit.model.User;

public interface UserDao {
	
	List<User> getUsers();
	
	User findByEmail(String email);
	
	User getUser(String email, String password);
	
	User addUser(User user);
	
	User deleteUser(long id);
	
	User updateUser(User user);

}
