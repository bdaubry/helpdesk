package com.brianaubry.helpdesk.controller;

import com.brianaubry.helpdesk.model.Ticket;
import com.brianaubry.helpdesk.model.User;
import com.brianaubry.helpdesk.repository.TicketRepository;
import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    UserService userService;

    @Autowired
    TicketRepository ticketRepository;

    @ModelAttribute("loggedInUser")
    public User populateUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());
        model.addAttribute("isUser", userService.isUser(loggedInUser));
        model.addAttribute("isAdmin", userService.isAdmin(loggedInUser));
        return loggedInUser;
    }

    @RequestMapping(value="")
    public String ticketIndex(Model model, @ModelAttribute User loggedInUser){
        populateUserDetails(model);
        model.addAttribute("ticket","this is the ticket page");
        return "ticket/index";
    }

    @GetMapping(value = "new")
    public String createNewTicket(Model model){

        model.addAttribute(new Ticket());

        return "ticket/new";
    }

    @PostMapping(value = "new")
    public String processTicketCreation(Model model, @Valid Ticket newTicket){


        ticketRepository.save(newTicket);

        return "redirect:/";
    }

}
