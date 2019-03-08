package com.brianaubry.helpdesk.controller;

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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("loggedInUser", user);
        model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
        model.addAttribute("isAdmin", user.getRoles().contains(roleRepository.findByRole("ADMIN")));
        model.addAttribute("isUser", user.getRoles().contains(roleRepository.findByRole("USER")));
        User newUser = new User();
        model.addAttribute("user", newUser);

        return "admin/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createNewAccount(Model model, @Valid User user, BindingResult bindingResult){
        User exists = userService.findUserByEmail(user.getEmail());

        if(exists != null){
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()){
            return "admin/create";
        } else {
            userService.saveUser(user);
            model.addAttribute("msg","User registered successfully");
            model.addAttribute("user", new User());
            return "admin/create";
        }
    }

}
