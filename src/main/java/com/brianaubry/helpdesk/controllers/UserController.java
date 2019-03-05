package com.brianaubry.helpdesk.controllers;

import com.brianaubry.helpdesk.models.User;
import com.brianaubry.helpdesk.service.SecurityService;
import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("login")
    public String login(Model model){

        model.addAttribute("title", "Login");

        return "user/login";
    }

    @PostMapping("login")
    public String handleLogin(Model model, @RequestParam String username, @RequestParam String password, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("error", "Username or password invalid");
            return "user/login";
        }


        securityService.autoLogin(username,password);

        return "redirect:";
    }

    @GetMapping("adminCreate")
    public void createAdminAccount(){
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setRoles("ADMIN");
    }

}
