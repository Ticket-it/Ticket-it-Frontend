package me.jdvp.androidaspectexample.APIModel.user;

public class LoginResponse {
    private String message;
    private String userId;
    private String type;
    private String name;
    private String userEmail;
    private String userPhone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public LoginResponse(String message, String userId, String type, String name, String userEmail, String userPhone) {
        this.message = message;
        this.userId = userId;
        this.type = type;
        this.name = name;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
