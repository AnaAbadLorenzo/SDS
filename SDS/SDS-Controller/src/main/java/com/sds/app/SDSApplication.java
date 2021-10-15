package com.sds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.sds.controller", "com.sds.service","com.sds.service.impl"})
@EntityScan({"com.sds.dao"})
@SpringBootApplication
public class SDSApplication {

	public static void main(String[] args) {
 		SpringApplication.run(SDSApplication.class, args);
	}

}