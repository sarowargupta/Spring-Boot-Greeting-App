package com.app.development.greeting.service;

import com.app.development.greeting.model.Greeting;
import com.app.development.greeting.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //UC-04 Save the greeting message in the repository

    private final GreetingRepository greetingRepository;

    //  Constructor Injection
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // Save greeting based on user input
    public Greeting saveGreeting(String firstName, String lastName) {
        String message = getGreetingMessage(firstName, lastName);

        // Save greeting message to DB
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);

    }

    //UC-05 find a greeting message by id in the repository

    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    //UC-06 List all the greeting messages in the repository
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }


    //UC-07 Edit a Greeting message in the repository

    // Update an existing greeting by ID
    public Optional<Greeting> updateGreeting(Long id, String newMessage) {
        Optional<Greeting> existingGreeting = greetingRepository.findById(id);

        if (existingGreeting.isPresent()) {
            Greeting greeting = existingGreeting.get();
            greeting.setMessage(newMessage);
            // Save updated greeting
            return Optional.of(greetingRepository.save(greeting));
        }

        return Optional.empty(); // Return empty if ID not found

    }
}