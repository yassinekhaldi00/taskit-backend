package com.khaldi.taskit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khaldi.taskit.dao.InvitationDao;
import com.khaldi.taskit.model.Invitation;
import com.khaldi.taskit.model.Task;

@Service
public class InvitationService {
	
	@Autowired
	InvitationDao invitationDao;
	
	@Transactional
	public List<Invitation> getInvitationsSending(long userId){
		return invitationDao.getInvitationsSending(userId);
	}
	
	@Transactional
	public List<Invitation> getInvitationsReceiving(long userId){
		return invitationDao.getInvitationsReceiving(userId);
	}
	
	@Transactional
	public Invitation getInvitation(long id) {
		return invitationDao.getInvitation(id);
	}
	
	@Transactional
	public boolean addInvitation(String email, long taskId, long senderId) {
		return invitationDao.addInvitation(email, taskId, senderId);
	}
	
	@Transactional
	public boolean acceptInvitation(long id) {
		return invitationDao.acceptInvitation(id);
	}
	
	@Transactional
	public boolean deleteInvitation(long id) {
		return invitationDao.deleteInvitation(id);
	}
	
	@Transactional
	public Invitation updateInvitation(Invitation invitation) {
		return invitationDao.updateInvitation(invitation);
	}
}
