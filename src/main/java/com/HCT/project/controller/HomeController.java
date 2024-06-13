package com.HCT.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //초기화면
    @GetMapping("/")
    public String index() {
        return "index";
    }
}