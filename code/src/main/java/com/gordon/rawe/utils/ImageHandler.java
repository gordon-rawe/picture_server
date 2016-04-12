package com.gordon.rawe.utils;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.process.StandardStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gordon on 16/4/7.
 */
public class ImageHandler {
    public static void resizeImage(String src, String dest, int width, int height) throws InterruptedException, IOException, IM4JavaException {
        ConvertCmd cmd = new ConvertCmd();//true for graphics magick
        IMOperation op = new IMOperation();
        op.addImage(src);
        op.resize(width, height);
        op.addImage(dest);
        cmd.setSearchPath("/usr/local/bin/");
        cmd.run(op);
        System.out.println(getImageInfo(src));
    }

    public static int[] getImageInfo(String imagePath) {
        String line = null;
        try {
            IMOperation op = new IMOperation();
//            op.format("width:%w,height:%h,path:%d%f,size:%b%[EXIF:DateTimeOriginal]");
            op.format("%w,%h");
            op.addImage(1);
            IdentifyCmd identifyCmd = new IdentifyCmd();
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.setSearchPath("/usr/local/bin/");
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            assert cmdOutput.size() == 1;
            line = cmdOutput.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] res = new int[2];
        if (line != null) {
            String[] split = line.split(",");
            res[0] = Integer.parseInt(split[0]);
            res[1] = Integer.parseInt(split[1]);
        }
        return res;
    }

}
