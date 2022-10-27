package com.revature;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class SpringBankApplication {

	public static void main(String[] args) {
		
		System.out.println(System.getenv("P4_HOST"));
		SpringApplication.run(SpringBankApplication.class, args);
	}
	
	

}
