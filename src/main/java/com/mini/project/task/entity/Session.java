package com.mini.project.task.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "session")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Valid
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sessionId;

	@NotNull
	@Valid
	@JoinColumn(name = "id")
	private Long intitatorUserId;

	@NotNull
	@Valid
	private boolean sessionActive;

	@OneToMany
	@JoinColumn(name = "participants_id")
	private List<User> participants;

	@OneToMany
	@JoinColumn(name = "session_id")
	private List<Restaurants> restaurants;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getIntitatorUserId() {
		return intitatorUserId;
	}

	public void setIntitatorUserId(Long intitatorUserId) {
		this.intitatorUserId = intitatorUserId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isSessionActive() {
		return sessionActive;
	}

	public void setSessionActive(boolean sessionActive) {
		this.sessionActive = sessionActive;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public List<Restaurants> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurants> restaurants) {
		this.restaurants = restaurants;
	}

}
