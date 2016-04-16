package com.gordon.rawe.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by gordon on 4/15/16.
 */
@ConfigurationProperties(prefix = "config")
public class Config {
    private int port;
    private String pictureAbsoluteDir;
    private String gmPath;
    private String[] sizes;

    public String[] getSizes() {
        return sizes;
    }

    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }

    public String getGmPath() {
        return gmPath;
    }

    public void setGmPath(String gmPath) {
        this.gmPath = gmPath;
    }

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
