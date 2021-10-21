package com.sds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sds.config.AuthFilter;

@ComponentScan(basePackages = { "com.sds.controller", "com.sds.config", "com.sds.service", "com.sds.dao" })
@SpringBootApplication
public class SDSApplication extends WebSecurityConfigurerAdapter {

	public static void main(final String[] args) {
		SpringApplication.run(SDSApplication.class, args);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthFilter authenticationTokenFilterBean() throws Exception {
		return new AuthFilter();
	}

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().and().csrf().disable();
	}

}