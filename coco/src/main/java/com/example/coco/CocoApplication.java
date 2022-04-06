package com.example.coco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CocoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocoApplication.class, args);
	}

}
