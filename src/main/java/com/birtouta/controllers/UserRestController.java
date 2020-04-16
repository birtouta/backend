package com.birtouta.controllers;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.services.Metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)

public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@PostMapping(path="/add")
	public ResponseEntity<?> addNewUser (@RequestBody User user, @RequestHeader("Token") String token) {
		
		
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			
			User u = userRepository.findByPhone(user.getPhone()) ;
			
			if (u != null) {
				return new ResponseEntity<String>("{\"success\":0, \"message\": \"user's phone number already exist in the database !\"}", HttpStatus.UNAUTHORIZED);
			}else {
				userRepository.save(user);
				return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
			}		
		}
		
	}
	
	@PostMapping(path="/disable")
	public ResponseEntity<?> disableUser (@RequestParam(required = true) String phone) {
		
		User u = userRepository.findByPhone(phone) ;
		System.out.println(u);
		
		if (u == null) {
			return new ResponseEntity<String>("{\"success\":0, "
					+ "\"message\": \"user's phone number does not exist in the database !\"}", HttpStatus.UNAUTHORIZED);
		}else {
		
			u.setDeleted(1);
			u.setUpdatedAt(Metier.getCurrentTimestamp());
			userRepository.save(u);
			return new ResponseEntity<String>("{\"success\":1,"
					+ "\"message\": \"the user has been disabled !\"}", HttpStatus.OK);
		}
	}
	
	@PostMapping(path="/enable")
	public ResponseEntity<?>  enableUser (@RequestParam(required = true) String phone) {
		
		User u = userRepository.findByPhone(phone) ;
		System.out.println(u);
		
		if (u == null) {
			return new ResponseEntity<String> ("{\"success\":0, "
					+ "\"message\": \"user's phone number does not exist in the database !\"}", HttpStatus.UNAUTHORIZED);
		}else {
			u.setDeleted(0);
			u.setUpdatedAt(Metier.getCurrentTimestamp());
			userRepository.save(u);
			return new ResponseEntity<String> ("{\"success\":1,"
					+ "\"message\": \"the user has been enabled !\"}", HttpStatus.OK);
		}
	}
	
	@PostMapping(path="/update") 
	public ResponseEntity<?> updateUser (@RequestBody User user, @RequestHeader("Token") String token ) {
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
	public ResponseEntity<?> checkUserPhone (@RequestBody String phone, @RequestHeader("Token") String token ) {
		
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
	
	
	@PostMapping(path="/all")
	public ResponseEntity<?> getAllUsers(@RequestHeader("Token") String token) {
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			return new ResponseEntity<List<User>> (userRepository.findAll(), HttpStatus.OK);
		}
	}
}


