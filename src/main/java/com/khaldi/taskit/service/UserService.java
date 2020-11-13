package com.khaldi.taskit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khaldi.taskit.dao.UserDao;
import com.khaldi.taskit.model.User;

@Service
public class UserService {
	
	 @Autowired 
	 private UserDao userDao;
	 
	 @Transactional
	 public List<User> getUsers(){
		 return userDao.getUsers();
	 }
	
	 @Transactional
	 public User getUser(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	 @Transactional
	 public User addUser(User user) {
		return userDao.addUser(user); 
	 }
	
	@Transactional
	public User deleteUser(long id) {
		return userDao.deleteUser(id);
	}
	
	@Transactional
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}
	 
	 
}
