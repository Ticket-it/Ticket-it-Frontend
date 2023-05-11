package me.jdvp.androidaspectexample.APIModel.user;

public class CreateUserRequest {

    private String email;
    private String password;
    private String fullName;
    private String mobileNo;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String email, String password, String fullName, String mobileNo) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
