package com.brianaubry.helpdesk.controller;

import com.brianaubry.helpdesk.model.Status;
import com.brianaubry.helpdesk.model.Ticket;
import com.brianaubry.helpdesk.model.Stage;
import com.brianaubry.helpdesk.model.User;
import com.brianaubry.helpdesk.repository.StatusRepository;
import com.brianaubry.helpdesk.repository.TicketRepository;
import com.brianaubry.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "ticket")
public class TicketController {

    @Autowired
    UserService userService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    StatusRepository statusRepository;

    private String id;

    @ModelAttribute("loggedInUser")
    public User populateUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());
        model.addAttribute("isUser", userService.isUser(loggedInUser));
        model.addAttribute("isAdmin", userService.isAdmin(loggedInUser));
        return loggedInUser;
    }

    @RequestMapping(value={"", "/"})
    public String ticketIndex(Model model, @ModelAttribute User loggedInUser){
        populateUserDetails(model);
        List<Ticket> openTickets = ticketRepository.findAll();
        model.addAttribute("openTickets", openTickets);

        //TODO: show only tickets that are assigned to the logged in user
        List<Ticket> assignedTickets = ticketRepository.findByAssignedToId(loggedInUser.getId());
        model.addAttribute("assigned", assignedTickets);
        System.out.println(assignedTickets);

        model.addAttribute("loggedInUserId", loggedInUser.getId());

        return "ticket/index";
    }


    @GetMapping(value = "new")
    public String createNewTicket(Model model){
        
        Ticket newTicket = new Ticket();
        model.addAttribute(newTicket);
        return "ticket/new";
    }

    @PostMapping(value = "new")
    public String processTicketCreation(Model model, @Valid Ticket newTicket, Errors errors){

        if(errors.hasErrors()){
            return "ticket/new";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());
        Date currentDate = new Date();
        newTicket.setDateOpened(currentDate);
        newTicket.setCreatedBy(loggedInUser);
        ticketRepository.save(newTicket);

        return "redirect:/ticket/" + newTicket.getId();
    }



    @RequestMapping(value="{id}")
    public String ticketIndexSingle(Model model, @PathVariable("id") int id){
        Ticket activeTicket = ticketRepository.findById(id);
        Status newStatus = new Status();

        List<Status> updates = activeTicket.getUpdates();
        Collections.reverse(updates);

        model.addAttribute("ticket", activeTicket);
        model.addAttribute("statuses", activeTicket.getUpdates());
        model.addAttribute("status", newStatus);
        model.addAttribute("stages", Stage.values());

        //TODO: updates are not showing in date order, need to ensure they are date-sorted

        return "ticket/ticket-detail";
    }

    @PostMapping(value = "{id}/add")
    public String addStatusUpdate(Model model, @PathVariable("id") int id, @Valid Status newStatus){
        Ticket activeTicket = ticketRepository.findById(id);
        User activeUser = populateUserDetails(model);
        newStatus.setAuthor(activeUser.getEmail());
        activeTicket.addUpdate(newStatus);
        activeTicket.setLastUpdated(new Date());
        statusRepository.save(newStatus);
        return "redirect:/ticket/" + id;
    }

    @PostMapping(value = "{id}/update")
    public String processTicketUpdate(Model model, @PathVariable("id") int id, @Valid Ticket ticket, Errors errors){

        Ticket activeTicket = ticketRepository.findById(id);

        activeTicket.setDescription(ticket.getDescription());
        activeTicket.setStage(ticket.getStage());
        ticketRepository.save(activeTicket);

        return "redirect:/ticket/" + id;
    }

    //TODO: close ticket process/methods, update ticket process/methods

}
