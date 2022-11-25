package com.bezkoder.spring.hibernate.onetomany;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Execute implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(Execute.class);

    @Override
    public void run(String... args) {
        log.info("Spartan Log4J");
    }
}
