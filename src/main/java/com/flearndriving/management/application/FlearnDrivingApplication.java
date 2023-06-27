package com.flearndriving.management.application;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlearnDrivingApplication {

	public static void main(String[] args) {
		System.out.println(NumberUtils.toScaledBigDecimal("adfsdjhfd"));
		SpringApplication.run(FlearnDrivingApplication.class, args);
	}

}
