package com.gordon.rawe.utils;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gordon on 16/4/7.
 */
public class ImageHandler {
    final static int RATIO_W = 1;
    final static int RATIO_H = 2;

    final static int TYPE_ONE = 1001;//ALL LARGER
    final static int TYPE_TWO = 1002;//WIDTH LARGER HEIGHT SMALLER
    final static int TYPE_THREE = 1003;//HEIGHT LARGER WIDTH SMALLER
    final static int TYPE_FOUR = 1004;//ALL SMALLER


    public static void resizeImage(String src, String dest,
                                   int width, int height, int srcWidth, int srcHeight)
            throws InterruptedException, IOException, IM4JavaException {
        ConvertCmd cmd = new ConvertCmd(true);//true for graphics magick
        IMOperation op = new IMOperation();
        op.addImage(src);
        int TYPE_SCALE = srcWidth > width ? srcHeight > height ? TYPE_ONE : TYPE_TWO :
                srcHeight > height ? TYPE_THREE : TYPE_FOUR;
        int RATIO_TYPE = srcWidth * 1f / srcHeight > width * 1f / height ?
                RATIO_W : RATIO_H;
        switch (TYPE_SCALE) {
            case TYPE_ONE:
                if (RATIO_TYPE == RATIO_W) {
                    int diff_x = srcHeight * width / height;
                    op.crop(srcWidth - diff_x, srcHeight, diff_x / 2, 0);
                } else {
                    int diff_y = srcWidth * height / width;
                    op.crop(srcWidth, srcHeight - diff_y, 0, diff_y / 2);
                }
                op.resize(width, height);
                break;
            case TYPE_TWO:
                break;
            case TYPE_THREE:
                break;
            case TYPE_FOUR:
                op.background("white");
                op.gravity("center");
                op.extent(width,height);
                break;
        }
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
            IdentifyCmd identifyCmd = new IdentifyCmd(true);
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
