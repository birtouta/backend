package com.birtouta.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.services.Metier;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user") 
public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@PostMapping(path="/addUser")
	public @ResponseBody String addNewUser (@RequestParam(required = true) String phone, @RequestParam(required = true) String password) {
		
		User u = userRepository.findByPhone(phone) ;
		
		if (! u.getPhone().isEmpty()) {
			return "{\"success\":0, \"message\": \"user's phone number already exist in the database !\"}";
		}else {
		
			User n = new User();
			n.setPhone(phone);
			n.setEmail(password);
			n.setCreated_at(Metier.getCurrentTimestamp());
			userRepository.save(n);
			return "{\"success\":1}";
		}
	}
	
	@DeleteMapping(path="/disableUser") 

	public @ResponseBody String disableUser (@RequestParam(required = true) String phone) {
		
		User u = userRepository.findByPhone(phone) ;
		System.out.println(u);
		
		if (u == null) {
			return "{\"success\":0, "
					+ "\"message\": \"user's phone number does not exist in the database !\"}";
		}else {
		
			u.setDeteled(1);
			u.setUpdated_at(Metier.getCurrentTimestamp());
			userRepository.save(u);
			return "{\"success\":1,"
					+ "\"message\": \"the user has been disabled !\"}";
		}
	}
	
	@PutMapping(path="/enableUser") 

	public @ResponseBody String enableUser (@RequestParam(required = true) String phone) {
		
		User u = userRepository.findByPhone(phone) ;
		System.out.println(u);
		
		if (u == null) {
			return "{\"success\":0, "
					+ "\"message\": \"user's phone number does not exist in the database !\"}";
		}else {
			u.setDeteled(0);
			u.setUpdated_at(Metier.getCurrentTimestamp());
			userRepository.save(u);
			return "{\"success\":1,"
					+ "\"message\": \"the user has been enabled !\"}";
		}
	}
	
	@PutMapping(path="/updateUser") 
	public @ResponseBody ResponseEntity<?> updateUser (@RequestBody User user, @RequestHeader("Token") String token ) {
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			
			User u = session.getUser() ;
			
			user.setId(u.getId());
			
			return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
		}
	}
	
	@PostMapping(path="/checkphone")
	public @ResponseBody ResponseEntity<?> checkUserPhone (@RequestParam(required = true) String phone, @RequestHeader("Token") String token ) {
		
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
		
			User user = session.getUser() ;
			
			if (user.getPhone().equals(phone)) {

				return new ResponseEntity<String>("Phone number exist !", HttpStatus.OK);
				
			}else {
				return new ResponseEntity<String>("phone does not exist", HttpStatus.UNAUTHORIZED);
			}
		}
	}
	
	
//	@GetMapping(path="/allUsers")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
	// This returns a JSON or XML with the users
	return userRepository.findAll();
	}
}


