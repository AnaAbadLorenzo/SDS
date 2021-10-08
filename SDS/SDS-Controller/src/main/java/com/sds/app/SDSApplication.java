package com.sds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.sds.controller")
@SpringBootApplication
public class SDSApplication {

	public static void main(String[] args) {
 		SpringApplication.run(SDSApplication.class, args);
	}

}