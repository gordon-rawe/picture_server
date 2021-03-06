package com.gordon.rawe.controller;

import com.gordon.rawe.Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gordon on 16/4/17.
 */
@Controller
public class WebVisitController {
    @RequestMapping("/all")
    public String listAllPictures(@RequestParam(value = "dimension", defaultValue = "original") String dimension,
                                  Model model) {
        File rootDir = new File(Application.PICTURE_ABSOLUTE_DIR + "/" + dimension);
        List<String> urls = new ArrayList<>();
        for (File picture : rootDir.listFiles()) {
            urls.add(Application.PUBLIC_IP + "/graphics/fetch?uuid=" + picture.getName().substring(0, 32));
        }
        System.out.println(urls);
        model.addAttribute("urls", urls);
        return "graphics";
    }
}
