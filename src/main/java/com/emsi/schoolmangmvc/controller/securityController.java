package com.emsi.schoolmangmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {

    @GetMapping("/403")
    public String youNotAutorized(){
        return "403";
    }

}
