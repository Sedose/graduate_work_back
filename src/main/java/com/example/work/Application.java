package com.example.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		sout(new BCryptPasswordEncoder().encode("password"));
		SpringApplication.run(Application.class, args);
	}

	private static void sout(String password) {
		System.out.println(password);
	}

}
