package com.gordon.rawe.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by gordon on 4/15/16.
 */
@ConfigurationProperties(prefix = "config")
public class Config {
    private int port;
    private String pictureAbsoluteDir;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPictureAbsoluteDir() {
        return pictureAbsoluteDir;
    }

    public void setPictureAbsoluteDir(String pictureAbsoluteDir) {
        this.pictureAbsoluteDir = pictureAbsoluteDir;
    }
}
