package com.gordon.rawe.controller;

import com.gordon.rawe.utils.FileUtils;
import com.gordon.rawe.utils.ImageHandler;
import com.gordon.rawe.utils.UUIDUtils;
import org.im4java.core.IM4JavaException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by gordon on 16/4/5.
 */
@RestController
public class Router {

    @RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> serveImages(
            @RequestParam(value = "dimension", defaultValue = "200_200") String dimension,
            @RequestParam(value = "uuid") String uuid) throws FileNotFoundException {
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
                        ImageHandler.resizeImage(originalFile,
                                FileUtils.getDirName("200_200", hashString), 200, 200
                                , originalSize[0], originalSize[1]);
                        ImageHandler.resizeImage(originalFile,
                                FileUtils.getDirName("400_400", hashString), 600, 600,
                                originalSize[0], originalSize[1]);
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
}
