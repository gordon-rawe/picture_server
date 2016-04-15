package com.gordon.rawe.utils;

import com.gordon.rawe.Application;

import java.io.File;

/**
 * Created by gordon on 16/4/5.
 */
public class FileUtils {


    public static File getFileFromDisk(String dimension, String uuid) {
        return new File(Application.PICTURE_ROOT_DIR + dimension + "/" + uuid + ".jpg");
    }

    public static String getDirName(String dimension, String uuid) {
        return Application.PICTURE_ABSOLUTE_DIR + dimension + "/" + uuid + ".jpg";
    }
}