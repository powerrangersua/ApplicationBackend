package com.powerrangers.backend.services;

public interface SecurityService {
    String findLoggedInLogin();

    void autoLogin(String login, String password);
}
