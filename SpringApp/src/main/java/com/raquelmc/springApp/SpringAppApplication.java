/*
 * Main
 */
package com.raquelmc.springApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


// TODO: Auto-generated Javadoc
/**
 * The Class SpringAppApplication.
 */
@SpringBootApplication
@EnableMongoAuditing
public class SpringAppApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}
}
