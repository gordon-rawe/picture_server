package com.gordon.rawe;

import com.gordon.rawe.settings.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({Config.class})
public class Application implements EmbeddedServletContainerCustomizer, CommandLineRunner {
    @Autowired
    Config config;

    public final static String PICTURE_ROOT_DIR = "src/main/resources/dimensions/";
    public static String PICTURE_ABSOLUTE_DIR = "/Users/gordon/spring/picture_server/code/src/main/resources/dimensions/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
//        container.setPort(config.getPort());
    }

    @Override
    public void run(String... args) throws Exception {
        Application.PICTURE_ABSOLUTE_DIR = config.getPictureAbsoluteDir();
    }
}
