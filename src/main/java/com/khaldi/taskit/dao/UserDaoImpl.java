package com.khaldi.taskit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.khaldi.taskit.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private EntityManager entityManager;
	

	@Override
	public List<User> getUsers() {
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery("from User");
		return query.list();
	}

	@Override
	public User getUser(String email) {
		User user = new User();
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery("from User where email =:email").
				setParameter("email",email);
		try {
			user = query.getSingleResult();
			user.setValid(true);
		}catch (NoResultException e) {
			user.setValid(false);
		}
		
		return user;
	}

	@Override
	public User addUser(User user) {
		Session session = entityManager.unwrap(Session.class);
		User userAdded = new User();
		@SuppressWarnings("unchecked")
		Query<User> query =  session.createQuery("from User where email=: email").setParameter("email", user.getEmail());
		try {
			query.getSingleResult();
			userAdded.setValid(false);
		}catch(NoResultException e) {
			String password = user.getPassword();
			user.setPassword(new BCryptPasswordEncoder().encode(password));
			session.save(user);
			userAdded.setEmail(user.getEmail());
			userAdded.setPassword(password);
			userAdded.setValid(true);
		}
		return userAdded;
	}

	@Override
	public boolean deleteUser(long id, String password) {
		Session session = entityManager.unwrap(Session.class);
		User user = session.load(User.class, id);
		if(new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			session.delete(user);
			return true;
		}
		return false;
	}

	@Override
	public User updateUser(User user) {
		User newUser = new User();
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery("from User where id=: id").setParameter("id", user.getId());
		try {
			newUser = query.getSingleResult();
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setEmail(user.getEmail());
			session.update(newUser);
		}
		catch(NoResultException e) {
			newUser.setValid(false);
		}
		return newUser;
		
	}
	@Override
	public boolean changePassword(long id, String password, String newPassword) {
		User user = new User();
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery("from User where id=: id ").setParameter("id", id);
		try {
			user = query.getSingleResult();
			if(new BCryptPasswordEncoder().matches(password, user.getPassword())) {
				user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
				session.update(user);
				return true;
			}else {
				return false;
			}
		}
		catch(NoResultException e) {
			return false;
		}

	}

}
