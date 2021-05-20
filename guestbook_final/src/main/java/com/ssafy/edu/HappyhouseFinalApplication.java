package com.ssafy.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HappyhouseFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyhouseFinalApplication.class, args);
	}

}
//http://localhost:8197/happyhouse/swagger-ui.html