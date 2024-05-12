package com.mini.project.task.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "restaurants")
public class Restaurants implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Valid
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	@Valid
	private String name;

	@Column(name = "vote")
	@Valid
	private int vote;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

}