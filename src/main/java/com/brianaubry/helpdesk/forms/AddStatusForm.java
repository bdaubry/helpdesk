package com.brianaubry.helpdesk.forms;

import com.brianaubry.helpdesk.model.Status;
import com.brianaubry.helpdesk.model.Ticket;

import javax.validation.constraints.NotNull;

public class AddStatusForm {

    private Ticket ticket;

    private Iterable<Status> statuses;

    @NotNull
    private int ticketId;

    @NotNull
    private int statusId;

    public AddStatusForm(Ticket ticket, Iterable<Status> statuses){
        this.ticket = ticket;
        this.statuses = statuses;
    }

    public AddStatusForm(){}

    public Ticket getTicket() {
        return ticket;
    }

    public Iterable<Status> getStatuses() {
        return statuses;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getStatusId() {
        return statusId;
    }
}
