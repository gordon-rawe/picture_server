package com.gordon.rawe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public final static String PICTURE_ROOT_DIR = "src/main/resources/dimensions/";
    public final static String PICTURE_ABSOLUTE_DIR = "/Users/gordon/spring/picture_server/code/src/main/resources/dimensions/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
