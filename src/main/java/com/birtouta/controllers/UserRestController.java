package com.birtouta.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.birtouta.dao.UserRepository;
import com.birtouta.entities.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user") 

public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/addUser")
	public @ResponseBody String addNewUser (@RequestParam(required = true) String phone, @RequestParam(required = true) String password) {
		
		User u = userRepository.searchByPhone(phone) ;
		
		if (! u.getPhone().isEmpty()) {
			return "{\"success\":0, \"message\": \"user's phone number already exist in the database !\"}";
		}else {
		
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			try {
				d = df.parse(df.format(new Timestamp(System.currentTimeMillis())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			User n = new User();
			n.setPhone(phone);
			n.setEmail(password);
			n.setCreated_at(d);
			userRepository.save(n);
			return "{\"success\":1}";
		}
	}
	
	@DeleteMapping(path="/disableUser") 

	public @ResponseBody String disableUser (@RequestParam(required = true) String phone) {
		
		User u = userRepository.searchByPhone(phone) ;
		System.out.println(u);
		
		if (u == null) {
			return "{\"success\":0, "
					+ "\"message\": \"user's phone number does not exist in the database !\"}";
		}else {
		
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			try {
				d = df.parse(df.format(new Timestamp(System.currentTimeMillis())));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			u.setDeteled(1);
			u.setUpdated_at(d);
			userRepository.save(u);
			return "{\"success\":1,"
					+ "\"message\": \"the user has been disabled !\"}";
		}
	}
	
	@PutMapping(path="/enableUser") 

	public @ResponseBody String enableUser (@RequestParam(required = true) String phone) {
		
		User u = userRepository.searchByPhone(phone) ;
		System.out.println(u);
		
		if (u == null) {
			return "{\"success\":0, "
					+ "\"message\": \"user's phone number does not exist in the database !\"}";
		}else {
		
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			try {
				d = df.parse(df.format(new Timestamp(System.currentTimeMillis())));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			u.setDeteled(0);
			u.setUpdated_at(d);
			userRepository.save(u);
			return "{\"success\":1,"
					+ "\"message\": \"the user has been enabled !\"}";
		}
	}
	
	@PutMapping(path="/updateUser") 

	public @ResponseBody String updateUser (@RequestParam User user) {
		
		User u = userRepository.searchByPhone(user.getPhone()) ;
		
		if (u.getPhone().isEmpty()) {
			return "{\"success\":0, \"message\": \"the user does not exist in the database !\"}";
		}else {
		
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			try {
				d = df.parse(df.format(new Timestamp(System.currentTimeMillis())));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			user.setUpdated_at(d);
			userRepository.save(user);
			return "{\"success\":1}";
		}
	}
	
//	@GetMapping(path="/allUsers")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
	// This returns a JSON or XML with the users
	return userRepository.findAll();
	}
}


