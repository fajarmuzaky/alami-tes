package com.java.alami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class AlamiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlamiApplication.class, args);
	}

}
