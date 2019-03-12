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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "ticket")
public class TicketController {

    @Autowired
    UserService userService;

    @Autowired
    TicketRepository ticketRepository;

    private String id;

    @ModelAttribute("loggedInUser")
    public User populateUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());
        model.addAttribute("isUser", userService.isUser(loggedInUser));
        model.addAttribute("isAdmin", userService.isAdmin(loggedInUser));
        return loggedInUser;
    }

    @RequestMapping(value="{id}")
    public String ticketIndexSingle(Model model, @PathVariable("id") int id){

        model.addAttribute("ticket", ticketRepository.findById(id));

        return "ticket/ticket-detail";
    }

    @RequestMapping(value={"", "/"})
    public String ticketIndex(Model model, @ModelAttribute User loggedInUser){
        populateUserDetails(model);
        model.addAttribute("ticket","this is the ticket page");
        List<Ticket> openTickets = ticketRepository.findAll();
        model.addAttribute("openTickets", openTickets);

        return "ticket/index";
    }


    @GetMapping(value = "new")
    public String createNewTicket(Model model){

        Ticket newTicket = new Ticket();
        model.addAttribute(newTicket);
        return "ticket/new";
    }

    @PostMapping(value = "new")
    public String processTicketCreation(Model model, @Valid Ticket newTicket){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());
        Date currentDate = new Date();
        newTicket.setDateOpened(currentDate);
        newTicket.setCreatedBy(loggedInUser);
        ticketRepository.save(newTicket);

        return "redirect:/ticket/" + newTicket.getId();
    }

}
