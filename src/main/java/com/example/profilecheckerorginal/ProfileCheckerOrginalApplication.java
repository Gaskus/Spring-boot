package com.example.profilecheckerorginal;

import com.example.profilecheckerorginal.service.ProfileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class ProfileCheckerOrginalApplication{
	public static void main(String[] args) {
			SpringApplication.run(ProfileCheckerOrginalApplication.class, args);
		}

}

