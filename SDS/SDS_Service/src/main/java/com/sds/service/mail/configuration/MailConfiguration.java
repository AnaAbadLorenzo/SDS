package com.sds.service.mail.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

	@Autowired
	public Environment env;

	@Bean
	public JavaMailSender getMailSenderConfiguration() {
		final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(env.getProperty("spring.mail.host"));
		javaMailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
		javaMailSender.setUsername(env.getProperty("spring.mail.username"));
		javaMailSender.setPassword(env.getProperty("spring.mail.password"));

		final Properties javaMailProperties = new Properties();

		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.starttls.required", "true");
		javaMailProperties.put("mail.smtp.connectiontimeout", "5000");
		javaMailProperties.put("mail.smtp.timeout", "5000");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.writetimeout", "5000");

		javaMailSender.setJavaMailProperties(javaMailProperties);

		return javaMailSender;
	}

}
