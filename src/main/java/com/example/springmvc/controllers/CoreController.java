package com.example.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoreController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}