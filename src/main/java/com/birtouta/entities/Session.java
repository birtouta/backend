package com.birtouta.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sessions")
public class Session implements Serializable{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
}
