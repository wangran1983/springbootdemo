package com.donbala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowIndexController {

    @GetMapping("/")
    public String showIndex() {
        System.out.println("index.jsp");
        return "index";
    }
}
