package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="configs")
public class Configuration implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	private String last_version; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at; 
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	   
	
	public Configuration () {};	

	public Configuration(String last_version, Date created_at, User user) {
		super();
		this.last_version = last_version;
		this.created_at = created_at;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLast_version() {
		return last_version;
	}

	public void setLast_version(String last_version) {
		this.last_version = last_version;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
