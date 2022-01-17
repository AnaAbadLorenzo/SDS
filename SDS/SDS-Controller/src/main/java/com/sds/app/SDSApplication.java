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
					.antMatchers(HttpMethod.GET, "/menu/*").permitAll().antMatchers(HttpMethod.GET, "/rol/*")
					.permitAll().antMatchers(HttpMethod.POST, "/rol").permitAll().antMatchers(HttpMethod.POST, "/rol/*")
					.permitAll().antMatchers(HttpMethod.GET, "/accion/*").permitAll()
					.antMatchers(HttpMethod.POST, "/accion").permitAll().antMatchers(HttpMethod.POST, "/accion/*")
					.permitAll().antMatchers(HttpMethod.GET, "/test/registrar/acciones").permitAll()
					.antMatchers(HttpMethod.GET, "/test/registrar/atributos").permitAll()
					.antMatchers(HttpMethod.GET, "/test/login/atributos").permitAll()
					.antMatchers(HttpMethod.GET, "/test/rol/atributos/guardar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/rol/atributos/modificar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/rol/accion/guardar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/rol/accion/modificar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/rol/accion/eliminar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/rol/accion/buscar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/accion/atributos/guardar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/accion/atributos/modificar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/accion/accion/buscar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/accion/accion/guardar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/accion/accion/modificar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/accion/accion/eliminar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/funcionalidad/atributos/modificar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/funcionalidad/atributos/guardar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/funcionalidad/accion/buscar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/funcionalidad/accion/guardar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/funcionalidad/accion/modificar").permitAll()
					.antMatchers(HttpMethod.GET, "/test/funcionalidad/accion/eliminar").permitAll()
					.antMatchers(HttpMethod.POST, "/registrar").permitAll().anyRequest().authenticated();

		}
	}

}