package com.khaldi.taskit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class TaskitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskitApplication.class, args);
	}

}
