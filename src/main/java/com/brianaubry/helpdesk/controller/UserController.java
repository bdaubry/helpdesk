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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model){
        return "user/login";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userHome(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("loggedInUser", user);
        model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
        model.addAttribute("isAdmin", user.getRoles().contains(roleRepository.findByRole("ADMIN")));

        return "user/index";
    }

}
