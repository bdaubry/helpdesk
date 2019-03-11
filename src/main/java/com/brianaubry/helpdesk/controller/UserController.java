package com.brianaubry.helpdesk.controller;

import com.brianaubry.helpdesk.model.Role;
import com.brianaubry.helpdesk.model.User;
import com.brianaubry.helpdesk.repository.RoleRepository;
import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        return "user/login";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userHome(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("loggedInUser", user);
        model.addAttribute("isUser", userService.isUser(user));
        model.addAttribute("isAdmin", userService.isAdmin(user));

        return "user/index";
    }

}
