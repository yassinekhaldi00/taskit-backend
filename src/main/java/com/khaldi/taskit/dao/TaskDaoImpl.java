package com.khaldi.taskit.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khaldi.taskit.model.Task;
import com.khaldi.taskit.model.User;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	private UserDao userDao;

	@Override
	public List<Task> getTasks(long userId) {
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<Task> query = session.createQuery("select tasks from User u where u.id=:id ").setParameter("id",userId);
		List<Task> tasks = query.list();
		return tasks;
	}

	@Override
	public Task getTask(long id) {
		Session session = entityManager.unwrap(Session.class);
		Task task = session.get(Task.class, id);
		return task;
	}

	@Override
	public Task addTask(Task task) {
		Session session = entityManager.unwrap(Session.class);
		session.save(task);
		return task;
	}

	@Override
	public Task updateTask(Task task) {
		Session session = entityManager.unwrap(Session.class);
		session.update(task);
		return task;
	}

	@Override
	public boolean deleteTask(long id) {
		Session session = entityManager.unwrap(Session.class);
		try {
			Task task = session.find(Task.class, id);
			session.remove(task);
			return true;
		}catch(NoResultException e) {
			return false;
		}
	}

	@Override
	public boolean deleteTasks(long userId) {
		Session session = entityManager.unwrap(Session.class);
		@SuppressWarnings("unchecked")
		Query<Task> query = session.createQuery("delete from Task t where t.user.id=:id ").setParameter("id", userId);
		try {
			query.executeUpdate();
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	@Override
	public boolean shareTask(String email, Task task) {
		User user = userDao.getUser(email);
		if (user.isValid()){
			Session session = entityManager.unwrap(Session.class);
			Task newTask = this.getTask(task.getId());
			Set<User> users = newTask.getUser();
			users.add(user);
			newTask.setUser(users);
			session.update(newTask);
			return true;
		}else {
			return false;
		}
		
	}
}
