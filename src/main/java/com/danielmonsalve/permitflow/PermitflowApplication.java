package com.danielmonsalve.permitflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PermitflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(PermitflowApplication.class, args);
		System.out.println("CONTRASEÑA ENCRIPTADA: " +
				new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("123456"));
	}

}
