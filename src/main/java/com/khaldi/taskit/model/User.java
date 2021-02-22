package com.khaldi.taskit.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table( name = "taskitUser")
@JsonIgnoreProperties("password")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="firstName", nullable = false)
	private String firstName;
	
	@Column(name="lastName",nullable = false)
	private String lastName;
	
	@Column(name="email",unique = true, nullable = false)
	private String email;

	@Column(name="password" , nullable = false)
	private String password;
	
	@ManyToMany( mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Task> tasks = new HashSet<>();
	
	@OneToMany( mappedBy = "sender", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"invitationsSending","invitationsReceiving"})
	private Set<Invitation> invitationsSending = new HashSet<>();
	
	@OneToMany( mappedBy = "receiver", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"invitationsSending","invitationsReceiving"})
	private Set<Invitation> invitationsReceiving = new HashSet<>();
	
	
	@Transient
	private boolean valid;
	
	@Transient
	private String jwt;

	
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}



	public long getId() {
		return id;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public Set<Invitation> getInvitationsSending() {
		return invitationsSending;
	}

	public void setInvitationsSending(Set<Invitation> invitationsSending) {
		this.invitationsSending = invitationsSending;
	}

	public Set<Invitation> getInvitationsReceiving() {
		return invitationsReceiving;
	}

	public void setInvitationReceving(Set<Invitation> invitationsReceiving) {
		this.invitationsReceiving = invitationsReceiving;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", valid=" + valid + "]";
	}

}
