package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Person;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.repository.PeopleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorldcupApplication {

	private static final Logger log = LoggerFactory.getLogger(WorldcupApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorldcupApplication.class, args);
	}
}
