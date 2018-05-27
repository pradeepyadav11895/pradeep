package com.pradeep.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexControllers {
    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
