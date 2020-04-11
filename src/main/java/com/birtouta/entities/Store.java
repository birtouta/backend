package com.birtouta.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="stores")
public class Store implements Serializable {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Date createdAt; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at") 
	private Date updatedAt; 
}
