package com.StarkIndustries.RadientDermat.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.StarkIndustries.RadientDermat")
public class RadientDermatApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadientDermatApplication.class, args);
	}

}
