package com.app.development.greeting.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    //UC-02 Service Layer to get simple Greeting message
    public String getGreetingMessage() {
        return "Hello World";
    }

    //UC-03 Ability for the Greeting App to give Greeting message
    //with user first name and last name

    public String getGreetingMessage(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            return "Hello, " + firstName + "!";
        } else if (lastName != null) {
            return "Hello, " + lastName + "!";
        } else {
            return "Hello World!";
        }
    }
}
