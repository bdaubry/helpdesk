package com.brianaubry.helpdesk.controller;

import com.brianaubry.helpdesk.model.Role;
import com.brianaubry.helpdesk.model.User;
import com.brianaubry.helpdesk.repository.RoleRepository;
import com.brianaubry.helpdesk.repository.UserRepository;
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
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @ModelAttribute("loggedInUser")
    public User populateUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());
        model.addAttribute("isUser", userService.isUser(loggedInUser));
        model.addAttribute("isAdmin", userService.isAdmin(loggedInUser));
        return loggedInUser;
    }

    @RequestMapping(value = "")
    public String adminConsole(Model model, @ModelAttribute User loggedInUser){

        //TODO: create admin console template and commands
        return "admin/admin";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model, @ModelAttribute User loggedInUser){

        populateUserDetails(model);
        model.addAttribute("userName", loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

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
            //TODO: allow user to login using either username or email
            char firstInitial = user.getFirstName().charAt(0);
            user.setUsername((firstInitial + user.getLastName()).toLowerCase());
            userService.saveUser(user);
            model.addAttribute("msg","User registered successfully");
            model.addAttribute("user", new User());
            return "admin/create";
        }
    }

    @RequestMapping(value = "users")
    public String manageUsers(Model model){

        List<User> allUsers = userRepository.findAll();

        model.addAttribute("allUsers", allUsers);

        return "admin/users";
    }

}
