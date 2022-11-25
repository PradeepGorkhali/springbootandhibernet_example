package com.bezkoder.spring.hibernate.onetomany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootOneToManyApplication {
  private static final Logger log = LoggerFactory.getLogger(Execute.class);

  public static void main(String[] args) {
    log.info("Test Log4J");

    SpringApplication.run(SpringBootOneToManyApplication.class, args);
    log.info("Test Log4J");
  }
}
