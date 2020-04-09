package com.birtouta;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.birtouta.BirtoutaApplication;
import com.birtouta.dao.UserRepository;
import com.birtouta.entities.User;

@SpringBootApplication
public class BirtoutaApplication implements CommandLineRunner {

	@Autowired(required=true)
	private UserRepository userRepository;
		
	public static void main(String[] args) {
		SpringApplication.run(BirtoutaApplication.class, args);
	}
	
	@Override 
	public void run(String... arg0) throws Exception{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		
		userRepository.save(new User("0540520325", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Oussama", "Boumaad", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2", df.parse(df.format(new Timestamp(System.currentTimeMillis())))));
		userRepository.save(new User("0556063697", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Abderrahmene", "Bendaoued", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2",df.parse(df.format(new Timestamp(System.currentTimeMillis())))));
		userRepository.save(new User("0550452604", "rootinfo", "Birtouta", "Alger", "boumaadoussama@gmail.com", 
				"Bilal", "Boumaad", "Rue flan ben flan", "35.6.25.252525", "26.26.25362.2",df.parse(df.format(new Timestamp(System.currentTimeMillis())))));

		
		userRepository.findAll().forEach(user -> {
			System.out.println(user.getPhone() +" => " + user.getFirst_name() +" => " + user.getLast_name() );
		});
	}

}
