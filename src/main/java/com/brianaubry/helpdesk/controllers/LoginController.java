package com.brianaubry.helpdesk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping(name="login", method= RequestMethod.GET)
    public String login(Model model){

        model.addAttribute("name", "Login");

        return "login";

    }

}
