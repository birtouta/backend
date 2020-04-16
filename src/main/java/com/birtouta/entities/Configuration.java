package com.birtouta.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name="configs")
@Data
public class Configuration implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	private String lastVersion; 
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
	private Timestamp createdAt; 
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Timestamp updatedAt; 
	
	@OneToOne
	@JoinColumn(name="id_user")
	@JsonBackReference
	private User user;	
	
}
