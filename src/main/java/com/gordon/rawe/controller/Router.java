package com.gordon.rawe.controller;

import com.alibaba.fastjson.JSON;
import com.gordon.rawe.Application;
import com.gordon.rawe.settings.Config;
import com.gordon.rawe.utils.FileUtils;
import com.gordon.rawe.utils.ImageHandler;
import com.gordon.rawe.utils.UUIDUtils;
import org.im4java.core.IM4JavaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gordon on 16/4/5.
 */
@RestController
public class Router {
    @Autowired
    Config config;

    @RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> serveImages(
            @RequestParam(value = "dimension", defaultValue = "original") String dimension,
            @RequestParam(value = "uuid") String uuid) throws FileNotFoundException {
        String[] sizes = config.getSizes();
        if (!Arrays.asList(sizes).contains(dimension)) dimension = "original";
        InputStreamResource inputStream = new InputStreamResource(new FileInputStream(FileUtils.getFileFromDisk(dimension, uuid)));
        return ResponseEntity.ok(inputStream);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        final String hashString = UUIDUtils.getUUID();
        try {
            BufferedOutputStream originalStream = new BufferedOutputStream(
                    new FileOutputStream(FileUtils.getFileFromDisk("original", hashString)));
            FileCopyUtils.copy(file.getInputStream(), originalStream);
            originalStream.close();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String originalFile = FileUtils.getDirName("original", hashString);
                        int[] originalSize = ImageHandler.getImageInfo(originalFile);
                        for (String size : config.getSizes()) {
                            int[] targetSize = FileUtils.parseSize(size);
                            ImageHandler.resizeImage(originalFile,
                                    FileUtils.getDirName(size, hashString), targetSize[0], targetSize[1]
                                    , originalSize[0], originalSize[1]);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IM4JavaException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return hashString;
        } catch (IOException e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @RequestMapping(value = "/uuid")
    public String getUUID() {
        return UUIDUtils.getUUID();
    }

    @RequestMapping(value = "/path")
    public String getPath() {
        return config.getPictureAbsoluteDir();
    }

    @RequestMapping(value = "/port")
    public int getPort() {
        return config.getPort();
    }

    @RequestMapping(value = "/root")
    public String getRootPath() {
        return new File("").getAbsolutePath();
    }

    @RequestMapping(value = "/sizes")
    public String getSizes() {
        String res = "";
        for (String str : config.getSizes()) {
            res += str + "\n";
        }
        return res;
    }

    @RequestMapping("/list")
    public String listPictures(@RequestParam(value = "dimension", defaultValue = "original") String dimension) {
        File rootDir = new File(Application.PICTURE_ABSOLUTE_DIR + "/" + dimension);
        List<String> urls = new ArrayList<>();
        for (File picture : rootDir.listFiles()) {
            urls.add(picture.getName().substring(0, 32));
        }
        return JSON.toJSONString(urls);
    }
}
