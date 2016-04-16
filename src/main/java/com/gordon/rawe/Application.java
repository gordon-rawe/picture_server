package com.gordon.rawe;

import com.gordon.rawe.settings.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.io.File;

@SpringBootApplication
@EnableConfigurationProperties({Config.class})
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer, CommandLineRunner {
    @Autowired
    Config config;

    public final static String PICTURE_ROOT_DIR = "src/main/resources/dimensions/";
    public static String PICTURE_ABSOLUTE_DIR = new File("").getAbsolutePath();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
//        container.setPort(config.getPort());
    }

    @Override
    public void run(String... args) throws Exception {
        Application.PICTURE_ABSOLUTE_DIR = config.getPictureAbsoluteDir();
        //如果没有,创建文件夹在相应的目录
        String[] dirsToCreate = new String[]{config.getPictureAbsoluteDir() + "original",
                config.getPictureAbsoluteDir() + "200_200",
                config.getPictureAbsoluteDir() + "400_400"};
        for (String dir : dirsToCreate) {
            File directory = new File(dir);
            if (!directory.exists()) directory.mkdir();
        }
    }
}
