package com.sds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sds.config.JWTAuthorizationFilter;

//@ComponentScan(basePackages = { "com.sds.controller", "com.sds.service", "com.sds.model", "com.sds.repository" })
@SpringBootApplication
@ComponentScan({ "com.sds.controller", "com.sds.service" })
@EntityScan("com.sds.model")
@EnableJpaRepositories("com.sds.repository")
public class SDSApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SDSApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
					.antMatchers(HttpMethod.GET, "/menu/funcionalidadesUsuario").permitAll()
					.antMatchers(HttpMethod.GET, "/menu/accionesFuncionalidad").permitAll()
					.antMatchers(HttpMethod.GET, "/rol/listarRol").permitAll()
					.antMatchers(HttpMethod.GET, "/rol/listarRoles").permitAll()
					.antMatchers(HttpMethod.GET, "/rol/listarRolesEliminados").permitAll()
					.antMatchers(HttpMethod.POST, "/rol").permitAll()
					.antMatchers(HttpMethod.POST, "/rol/modificarRol").permitAll()
					.antMatchers(HttpMethod.POST, "/rol/eliminarRol").permitAll()
					.antMatchers(HttpMethod.POST, "/registro").permitAll().anyRequest().authenticated();

		}
	}

}