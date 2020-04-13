package com.birtouta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;
import com.birtouta.services.Metier;

@RestController
@RequestMapping(path="/auth") 
public class AuthRestController {

	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@PostMapping(path="/login")
	public ResponseEntity<?>  logIn(@RequestParam String phone, @RequestParam String password, @RequestHeader("Token") String token) {
		
		Session session = sessionRepository.findByToken(token);
		if(session == null) {
			// check the token if it exist
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			
			// check the user phone number if it's registered 
			User user = userRepository.findByPhone(phone);
			
			if (user == null) {
				return new ResponseEntity<String>("Phone number does not exist", HttpStatus.UNAUTHORIZED);

			}else {
				if (user.getPassword().equals(password)) {

					session.setLoginAt(Metier.getCurrentTimestamp());
					session.setUpdatedAt(Metier.getCurrentTimestamp());

					sessionRepository.save(session);
					
					return ResponseEntity.ok(user);
					
				}else {
					return new ResponseEntity<String>("User and password does not match", HttpStatus.UNAUTHORIZED);
				}
			}
			
		}
	}
	
	
	@PostMapping(path="/logout")
	public ResponseEntity<?>  logOut(@RequestHeader("Token") String token) {
		Session session = sessionRepository.findByToken(token);
		
		if(session == null) {
			// check if the token exists
			
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			// update updateAt and logoutAt attributes and return logout status ok 
			
			session.setUpdatedAt(Metier.getCurrentTimestamp());
			session.setLogoutAt(Metier.getCurrentTimestamp());
			return new ResponseEntity<String>("Logout successfully !", HttpStatus.OK);
		}
	
	}
	
	@PostMapping(path="/signup")
	public ResponseEntity<?>  signIn(@RequestBody User user ,@RequestHeader("Token") String token) {
		Session session = sessionRepository.findByToken(token);
		
		if(session == null) {
			// check if the token exists
			return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
		}else {
			//check if the user exists already!
			if (userRepository.findByPhone(user.getPhone()) == null) {
				User u = new User();
				u = userRepository.save(user); 
				session.setUser(u);
				return new ResponseEntity<String>("Registred successfully !", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("User's phone number exists in the system, try another phone number !", HttpStatus.UNAUTHORIZED);
			}
		}
	}
}
