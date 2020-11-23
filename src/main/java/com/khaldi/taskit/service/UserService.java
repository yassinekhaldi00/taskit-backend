package com.khaldi.taskit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khaldi.taskit.UserPrincipal;
import com.khaldi.taskit.dao.UserDao;
import com.khaldi.taskit.model.User;

@Service
public class UserService  implements UserDetailsService{
	
	 @Autowired 
	 private UserDao userDao;
	 
	 @Transactional
	 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.getUser(email);
		if(!user.isValid()) {
			 throw new UsernameNotFoundException("Not found!");
		}
		return new UserPrincipal(user);
	 }
	 
	 @Transactional
	 public List<User> getUsers(){
		 return userDao.getUsers();
	 }
	
	 @Transactional
	 public User getUser(String email) {
		return userDao.getUser(email);
	}
	
	 @Transactional
	 public User addUser(User user) {
		return userDao.addUser(user); 
	 }
	
	@Transactional
	public boolean deleteUser(long id, String password) {
		return userDao.deleteUser(id, password);
	}
	
	@Transactional
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	@Transactional
	public boolean changePassword(long id, String password, String newPassword) {
		return userDao.changePassword(id, password, newPassword);
	}
	 
}
