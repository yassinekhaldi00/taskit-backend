package com.khaldi.taskit.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
		@SuppressWarnings("unchecked")
		Query<User> query =  session.createQuery("from User where email=: email").setParameter("email", user.getEmail());
		try {
			query.getSingleResult();
			user.setValid(false);
		}catch(NoResultException e) {
			session.save(user);
			user.setValid(true);
		}
		return user;
	}

	@Override
	public User deleteUser(long id) {
		Session session = entityManager.unwrap(Session.class);
		User user = session.find(User.class, id);
		session.remove(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		User newUser = new User();
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery("from User where email=: email").setParameter("email", user.getEmail());
		try {
			newUser = query.getSingleResult();
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setPassword(user.getPassword());
			session.update(newUser);
		}
		catch(NoResultException e) {
			newUser.setValid(false);
		}
		return newUser;
		
	}

}
