package com.k_konsult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KKonsultApplication {

	public static void main(String[] args) {
		SpringApplication.run(KKonsultApplication.class, args);
	}

}
