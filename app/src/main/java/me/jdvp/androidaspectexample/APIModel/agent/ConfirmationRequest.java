package me.jdvp.androidaspectexample.APIModel.agent;

public class ConfirmationRequest {
    private String status;

    public ConfirmationRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
