package me.jdvp.androidaspectexample.APIModel.Events;

import java.util.List;

public class HistorySubResponse {
    private List<TicketsHistoryResponse> tickets;

    public HistorySubResponse(List<TicketsHistoryResponse> tickets) {
        this.tickets = tickets;
    }

    public List<TicketsHistoryResponse> getTickets() {
        return tickets;
    }
}