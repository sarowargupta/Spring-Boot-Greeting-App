package com.app.development.greeting.controller;
import com.app.development.greeting.model.Greeting;
import com.app.development.greeting.service.GreetingService;
import org.springframework.web.bind.annotation.*;

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

}
