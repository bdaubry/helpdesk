package com.brianaubry.helpdesk.controller;

import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"", "/"})
    public String index(Model model, HttpServletRequest request){

        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("title", "login");
//        User adminExists = userService.findUserByEmail("admin@admin.com");
//
//        if(adminExists == null){
//            User admin = new User();
//            admin.setFirstname("admin");
//            admin.setLastname("admin");
//            admin.setEmail("admin@admin.com");
//            admin.setPassword("admin");
//            userService.saveUser(admin);
//        }
//
//        System.out.println(adminExists.getPassword());

        return "login";
    }





}
