package com.gordon.rawe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public final static String UPLOAD_DIR = "src/main/resources/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
