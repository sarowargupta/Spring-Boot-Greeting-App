package com.app.development.greeting.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    //UC-02 Service Layer to get simple Greeting message
    public String getGreetingMessage() {
        return "Hello World";
    }
}
