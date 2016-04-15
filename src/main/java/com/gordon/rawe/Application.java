package com.gordon.rawe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {
    public final static String PICTURE_ROOT_DIR = "src/main/resources/dimensions/";
    public final static String PICTURE_ABSOLUTE_DIR = "/Users/gordon/spring/picture_server/code/src/main/resources/dimensions/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(1128);
    }
}
