package com.finconsgroup.appostman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement
public class Configuration {
		
	public static void main(String[] args) {
		SpringApplication.run(Configuration.class, args);
	}

}
