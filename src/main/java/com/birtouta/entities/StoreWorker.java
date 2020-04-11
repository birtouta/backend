package com.birtouta.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@SuppressWarnings("serial")
@Entity
@Table(name="store_workers")
public class StoreWorker implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	private String accessCode; 
	
	@OneToOne
	@JoinColumn(name="id_user")
	@JsonBackReference
	private User user;
	
	@OneToOne
	@JoinColumn(name="id_store")
	@JsonBackReference
	private Store store;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}
