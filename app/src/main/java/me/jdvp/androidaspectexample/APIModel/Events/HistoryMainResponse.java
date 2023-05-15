package me.jdvp.androidaspectexample.APIModel.Events;

public class HistoryMainResponse {
    private HistorySubResponse history;

    public HistoryMainResponse(HistorySubResponse history) {
        this.history = history;
    }

    public HistorySubResponse getHistory() {
        return history;
    }
}