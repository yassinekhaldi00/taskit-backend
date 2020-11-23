package com.khaldi.taskit.dao;

import java.util.List;

import com.khaldi.taskit.model.User;

public interface UserDao {
	
	List<User> getUsers();
	
	User getUser(String email);
	
	User addUser(User user);
	
	boolean deleteUser(long id, String password);
	
	User updateUser(User user);
	
	boolean changePassword(long id, String password, String newPassword);

}
