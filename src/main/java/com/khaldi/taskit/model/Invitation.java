package com.khaldi.taskit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "invitation")
public class Invitation {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	private String state;
	
	@ManyToOne
	@JsonIgnoreProperties({"tasks", "invitationsSending","invitationsReceiving","password"})
	private User sender;
	
	@ManyToOne
	@JsonIgnoreProperties({"tasks", "invitationsSending","invitationsReceiving","password"})
	private User receiver;
	
	@ManyToOne
	@JsonIgnoreProperties({"user", "invitations"})
	private Task task;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
