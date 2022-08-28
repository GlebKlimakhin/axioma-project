package com.axioma.axiomatrainee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:application-work.yaml")
public class AxiomaTraineeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxiomaTraineeApplication.class, args);
    }

}
