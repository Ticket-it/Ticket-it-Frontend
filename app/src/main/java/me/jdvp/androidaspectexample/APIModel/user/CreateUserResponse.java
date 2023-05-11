package me.jdvp.androidaspectexample.APIModel.user;

public class CreateUserResponse {
    private String message;

    public CreateUserResponse() { }

    public CreateUserResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
