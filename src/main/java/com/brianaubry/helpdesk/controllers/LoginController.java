package com.brianaubry.helpdesk.controllers;

import com.brianaubry.helpdesk.models.User;
import com.brianaubry.helpdesk.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(name="login", method= RequestMethod.GET)
    public String login(Model model){

        model.addAttribute("title", "Login");

        return "login";
    }

    @RequestMapping(name="login", method=RequestMethod.POST)
    public String handleLogin(Model model, @RequestParam String username, @RequestParam String password){

        User userToLogin = userDao.findOne(name=username);

        return "redirect:";
    }

}
