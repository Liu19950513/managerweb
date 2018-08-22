package com.dhu.managerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ManagerwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerwebApplication.class, args);
	}
}
