package com.example.dto;

public class LoginCourierRequest {

    private final String login;

    private final String password;

    public LoginCourierRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
