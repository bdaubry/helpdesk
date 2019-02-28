package com.brianaubry.helpdesk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/u")
public class UserController {

    @RequestMapping("/u")
    public String login(){
        return "login";
    }

}
