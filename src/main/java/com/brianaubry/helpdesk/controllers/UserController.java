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

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String login(Model model){

        model.addAttribute("title", "Login");

        return "user/login";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public String handleLogin(Model model, @RequestParam String username, @RequestParam String password, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("error", "Username or password invalid");
            return "user/login";
        }

        securityService.autoLogin(username,password);

        return "redirect:";
    }

    @RequestMapping(value="register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model){

        model.addAttribute(new User());

        return "user/register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String handleRegistration(Model model, @ModelAttribute User newUser, Errors errors) {

        if(errors.hasErrors()) {
            return "user/register";
        }

        Character firstLetter = newUser.getFirstName().charAt(0);

        String username = firstLetter.toString() + newUser.getLastName();

        newUser.setUsername(username.toLowerCase());

        userService.save(newUser);

        return "user/register";
    }

}
