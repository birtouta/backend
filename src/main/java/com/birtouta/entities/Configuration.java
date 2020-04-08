package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Configuration implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String last_version; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at; 
	
	@OneToOne
    @MapsId
    private User user;
	 
	
}
