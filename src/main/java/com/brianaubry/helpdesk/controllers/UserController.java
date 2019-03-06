package com.brianaubry.helpdesk.controllers;

import com.brianaubry.helpdesk.models.User;
import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String login(Model model){

        model.addAttribute("title", "Login");

        return "user/login";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public String handleLogin(Model model, @RequestParam String username, @RequestParam String password, Errors errors){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());

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
