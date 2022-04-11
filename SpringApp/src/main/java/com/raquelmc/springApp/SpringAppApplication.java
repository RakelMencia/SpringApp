/*
 * Main
 */
package com.raquelmc.springApp;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
	
	/** The Constant LOG_RAIZ. */
	private final static Logger LOG_RAIZ = Logger.getLogger(Constants.MAIN_PACK);
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(Constants.MAIN_LOG);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		initializeLogs();
		SpringApplication.run(SpringAppApplication.class, args);				
	}
	
	/**
	 * Initialize logs.
	 */
	private static void initializeLogs() {
		try {
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler(Constants.LOG_FILENAME, false);//Creamos un fichero de log para todos los errores.
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			LOG_RAIZ.addHandler(consoleHandler);
			LOG_RAIZ.addHandler(fileHandler);
			consoleHandler.setLevel(Level.ALL);
	        fileHandler.setLevel(Level.ALL);
		} catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error de IO");
        } catch (SecurityException ex) {
            LOGGER.log(Level.SEVERE, "Error de Seguridad");
        } 
    }
}
