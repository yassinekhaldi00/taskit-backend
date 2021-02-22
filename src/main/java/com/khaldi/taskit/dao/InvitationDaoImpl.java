package com.khaldi.taskit.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khaldi.taskit.model.Invitation;
import com.khaldi.taskit.model.Task;
import com.khaldi.taskit.model.User;

@Repository
public class InvitationDaoImpl implements InvitationDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired 
	private TaskDao taskDao;
	
	@Override
	public List<Invitation> getInvitationsSending(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query<Invitation> query = session.createQuery("select invitationsSending from User u where u.id=:id ").setParameter("id",userId);
		List<Invitation> invitationsSending = query.list();
		return invitationsSending;
	}

	@Override
	public List<Invitation> getInvitationsReceiving(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query<Invitation> query = session.createQuery("select invitationsReceiving from User u where u.id=:id ").setParameter("id",userId);
		List<Invitation> invitationsReceiving = query.list();
		return invitationsReceiving;
	}

	@Override
	public boolean addInvitation(String email, long taskId, long senderId) {
		Session session = entityManager.unwrap(Session.class);
		Query<Invitation> query = session.createQuery("from Invitation i where i.task.id=:id ").setParameter("id",taskId);
		List<Invitation> invitations = query.list();
		if (invitations.size() <= 0) {
			User reciever = userDao.getUser(email);
			User sender = session.find(User.class, senderId);
			if (reciever.isValid()){
				Invitation invitation = new Invitation();
				Task task = taskDao.getTask(taskId);
				invitation.setTask(task);
				invitation.setState("send");
				invitation.setReceiver(reciever);
				invitation.setSender(sender);
				session.save(invitation);
				return true;
			}else {
				return false;
			}
		}
		return true;
		
	}
	
	@Override
	public Invitation getInvitation(long id){
		Session session = entityManager.unwrap(Session.class);
		try {
			Invitation  invitation = session.find(Invitation.class, id);
			return invitation;
		}catch(NoResultException e) {
			return null;
		}	
		
	}
	
	@Override
	public boolean acceptInvitation(long id) {
		Session session = entityManager.unwrap(Session.class);
		Invitation invitation = this.getInvitation(id);
		if (invitation != null) {
			Task task = taskDao.getTask(invitation.getTask().getId());
			Set<User> users = task.getUser();
			User receiver = invitation.getReceiver();
			users.add(receiver);
			task.setUser(users);
			session.update(task);
			this.deleteInvitation(id);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteInvitation(long id) {
		Session session = entityManager.unwrap(Session.class);
		try {
			Invitation invitation = session.find(Invitation.class, id);
			session.remove(invitation);
			return true;
		}catch(NoResultException e) {
			return false;
		}		
	}

	@Override
	public Invitation updateInvitation(Invitation invitation) {
		Session session = entityManager.unwrap(Session.class);
		session.update(invitation);
		return invitation;
	}

}
