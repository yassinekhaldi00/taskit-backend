package com.khaldi.taskit.dao;

import java.util.List;

import com.khaldi.taskit.model.Invitation;
import com.khaldi.taskit.model.Task;

public interface InvitationDao {
	
	List<Invitation> getInvitationsSending(long userId);
	
	List<Invitation> getInvitationsReceiving(long userId);
	
	Invitation getInvitation(long id);
	
	boolean addInvitation(String email, long taskId, long senderId);
	
	boolean acceptInvitation(long id);
	
	boolean deleteInvitation(long id);
	
	Invitation updateInvitation(Invitation invitation);
	
}
