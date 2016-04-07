package com.gordon.rawe.controller;

import com.gordon.rawe.Application;
import com.gordon.rawe.utils.FileUtils;
import com.gordon.rawe.utils.UUIDUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.UUID;

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
        String hashString = UUIDUtils.getUUID();
        try {
            BufferedOutputStream stream_200_200 = new BufferedOutputStream(
                    new FileOutputStream(FileUtils.getFileFromDisk("200_200", hashString)));
            BufferedOutputStream stream_400_400 = new BufferedOutputStream(
                    new FileOutputStream(FileUtils.getFileFromDisk("400_400", hashString)));
            FileCopyUtils.copy(file.getInputStream(), stream_200_200);
            FileCopyUtils.copy(file.getInputStream(), stream_400_400);
            stream_200_200.close();
            stream_200_200.close();
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
