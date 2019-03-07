package com.brianaubry.helpdesk.controller;

import com.brianaubry.helpdesk.model.User;
import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model){
        return "user/login";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String signup(Model model){

        User newUser = new User();
        model.addAttribute("user", newUser);

        return "user/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String processSignup(Model model, @Valid User user, BindingResult bindingResult){
        User exists = userService.findUserByEmail(user.getEmail());

        if(exists != null){
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()){
            return "user/create";
        } else {
            userService.saveUser(user);
            model.addAttribute("msg","User registered successfully");
            model.addAttribute("user", new User());
            return "user/create";
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userHome(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());

        return "user/index";
    }

}
