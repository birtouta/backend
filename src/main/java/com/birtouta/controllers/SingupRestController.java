package com.birtouta.controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.birtouta.dao.SessionRepository;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.Session;
import com.birtouta.entities.User;


@RestController
@RequestMapping(path="/auth") 
public class SingupRestController {
	
	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	@PostMapping(path="/singnup")
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