package com.gordon.rawe.utils;

import com.gordon.rawe.Application;
import com.gordon.rawe.settings.Config;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Created by gordon on 16/4/5.
 */
public class FileUtils {
    @Autowired
    Config config;

    public static File getFileFromDisk(String dimension, String uuid) {
        return new File(Application.PICTURE_ABSOLUTE_DIR + dimension + "/" + uuid + ".jpg");
    }

    public static String getDirName(String dimension, String uuid) {
        return Application.PICTURE_ABSOLUTE_DIR + dimension + "/" + uuid + ".jpg";
    }

    public static int[] parseSize(String sizeString) {
        String[] sizes = new String[2];
        if (sizeString.contains("_"))
            sizes = sizeString.split("_");
        return new int[]{Integer.parseInt(sizes[0]), Integer.parseInt(sizes[1])};
    }
}