package com.danidev.MyGuide.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeWebController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }
}
