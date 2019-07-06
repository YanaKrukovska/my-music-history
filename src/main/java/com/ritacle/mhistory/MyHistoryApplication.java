package com.ritacle.mhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.ritacle.mhistory.persistence.repository")
@EntityScan("com.ritacle.mhistory.persistence.model")
@SpringBootApplication
public class MyHistoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyHistoryApplication.class, args);
    }

}
