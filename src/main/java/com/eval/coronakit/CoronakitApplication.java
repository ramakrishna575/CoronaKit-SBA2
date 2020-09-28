package com.eval.coronakit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@Configuration
@ComponentScan(basePackages = "com.eval.coronakit")
//@EnableAutoConfiguration*/
public class CoronakitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronakitApplication.class, args);
		
	}

}
