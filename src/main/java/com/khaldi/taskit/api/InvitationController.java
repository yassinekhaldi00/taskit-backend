package com.khaldi.taskit.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.khaldi.taskit.model.Invitation;
import com.khaldi.taskit.model.Task;
import com.khaldi.taskit.service.InvitationService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/invitation")
public class InvitationController {
	
	@Autowired
	InvitationService invitationService;
	
	@GetMapping("/sender/{id}")
	public List<Invitation> getInvitationsSending(@PathVariable("id") long userId){
		return invitationService.getInvitationsSending(userId);
	}
	
	@GetMapping("/receiver/{id}")
	public List<Invitation> getInvitationsReceiving(@PathVariable("id") long userId){
		return invitationService.getInvitationsReceiving(userId);
	}
	
	@GetMapping("/{id}")
	public Invitation getInvitation(@PathVariable("id") long id) {
		return invitationService.getInvitation(id);
	}
	
	@PostMapping
	public boolean addInvitation(@RequestBody ObjectNode objectNode) throws JsonProcessingException {
		String email = objectNode.get("email").asText();
		long senderId = objectNode.get("senderId").asLong();
		//ObjectMapper mapper = new ObjectMapper();
		//Task task = mapper.treeToValue(objectNode.get("task"), Task.class);
		long taskId = objectNode.get("taskId").asLong();
		return invitationService.addInvitation(email, taskId, senderId);
	}
	
	@PostMapping("/accept/{id}")
	public boolean acceptInvitation(@PathVariable("id") long id) {
		return invitationService.acceptInvitation(id);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteInvitation(@PathVariable("id") long id) {
		return invitationService.deleteInvitation(id);
	}
	
	@PutMapping
	public Invitation updateInvitation(@RequestBody Invitation invitation) {
		return invitationService.updateInvitation(invitation);
	}

}
