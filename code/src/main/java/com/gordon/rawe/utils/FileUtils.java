package com.gordon.rawe.utils;

import java.io.File;

/**
 * Created by gordon on 16/4/5.
 */
public class FileUtils {
    public final static String PICTURE_ROOT_DIR = "src/main/resources/dimensions/";

    public static File getFileFromDisk(String dimension, String uuid) {
        return new File(PICTURE_ROOT_DIR + dimension + "/" + uuid + ".jpg");
    }
}