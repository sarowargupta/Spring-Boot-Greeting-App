package com.app.development.greeting.controller;
import com.app.development.greeting.model.Greeting;
import com.app.development.greeting.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    //UC-1 Return Json for different HTTP Methods

    @GetMapping("/greet")
    public Greeting getGreet() {
        return new Greeting("Hello, GET!");
    }

    @PostMapping("/greet")
    public Greeting postGreeting(@RequestBody Greeting greeting) {
        return new Greeting("Hello, POST! Received: " + greeting.getMessage());
    }

    @PutMapping("/greet")
    public Greeting putGreeting(@RequestBody Greeting greeting) {
        return new Greeting("Hello, PUT! Updated: " + greeting.getMessage());
    }

    @DeleteMapping("/greet")
    public Greeting deleteGreeting() {
        return new Greeting("Hello, DELETE! Resource removed.");
    }

    //UC-02 Service Layer to get simple Greeting message

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public Greeting getGreeting() {
        return new Greeting(greetingService.getGreetingMessage());
    }

    //UC-03 Ability for the Greeting App to give Greeting message
    //with user first name and last name

    @GetMapping("/hi")
    public Greeting getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        String message = greetingService.getGreetingMessage(firstName, lastName);
        return new Greeting(message);
    }

    //UC-04 Save the greeting message in the repository

    @PostMapping("/query")
    public Greeting saveGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return greetingService.saveGreeting(firstName, lastName);
    }

    //UC-05 find a greeting message by id in the repository

    //  Find a greeting by ID
    @GetMapping("/{id}")
    public Optional<Greeting> getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    //UC-06 List all the greeting messages in the repository
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    //UC-07 Edit a Greeting message in the repository

    // Update a greeting by ID
    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable Long id, @RequestParam String message) {
        Optional<Greeting> updatedGreeting = greetingService.updateGreeting(id, message);

        return updatedGreeting
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return empty if not found
    }

    //UC-08 Delete  a greeting message in the repository

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        boolean isDeleted = greetingService.deleteGreeting(id);
        if (isDeleted) {
            return ResponseEntity.ok("Greeting with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
