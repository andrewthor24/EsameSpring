package it.devlecc.esamespring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EsamespringApplication {

	private static final Logger logger = LoggerFactory.getLogger(EsamespringApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(EsamespringApplication.class, args);
		logger.debug("inizio dell'applicazione spring");
	}

}