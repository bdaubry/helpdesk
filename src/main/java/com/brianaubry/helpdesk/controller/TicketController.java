package com.brianaubry.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @RequestMapping(value="/")
    public String ticketIndex(Model model){
        model.addAttribute("ticket","this is the ticket page");
        return "ticket/index";
    }

}
