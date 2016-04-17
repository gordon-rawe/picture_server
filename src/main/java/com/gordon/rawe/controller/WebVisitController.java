package com.gordon.rawe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gordon on 16/4/17.
 */
@Controller
public class WebVisitController {
    @RequestMapping("/all")
    public String greeting() {
        return "graphics";
    }

}
