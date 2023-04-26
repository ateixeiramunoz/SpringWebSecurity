package com.eoi.springwebsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringWebSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebSecurityApplication.class, args);
	}

}
