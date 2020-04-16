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

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name="store_workers")
@Data
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
	
}
