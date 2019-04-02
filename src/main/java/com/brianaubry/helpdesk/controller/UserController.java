package com.brianaubry.helpdesk.controller;

import com.brianaubry.helpdesk.model.Role;
import com.brianaubry.helpdesk.model.Ticket;
import com.brianaubry.helpdesk.model.User;
import com.brianaubry.helpdesk.repository.RoleRepository;
import com.brianaubry.helpdesk.repository.TicketRepository;
import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketRepository ticketRepository;

    @ModelAttribute("loggedInUser")
    public User populateUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());
        model.addAttribute("isUser", userService.isUser(loggedInUser));
        model.addAttribute("isAdmin", userService.isAdmin(loggedInUser));
        return loggedInUser;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userHome(Model model, @ModelAttribute User loggedInUser){
        populateUserDetails(model);

        return "user/index";
    }

    @RequestMapping(value = "{id}")
    public String userPage(Model model, @PathVariable("id") int id){
        //TODO: create stats page
        return "user/stats";
    }

    @RequestMapping(value = "{id}/queue")
    public String userQueue(Model model, @PathVariable("id") int id){
        List<Ticket> openTickets = ticketRepository.findAll();
        model.addAttribute("userTickets", openTickets);
        //TODO: create user queue page
        return "user/queue";
    }

    @RequestMapping(value = "{id}/account")
    public String userSettings(Model model, @PathVariable("id") int id){
        //TODO: create settings page
        return "user/stats";
    }
}
