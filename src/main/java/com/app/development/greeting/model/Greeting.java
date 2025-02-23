package com.app.development.greeting.model;
import jakarta.persistence.*;

//UC-04 Save the greeting message in the repository
//database using spring data jpa it must be converted into entity

@Entity  // Marks this class as a JPA Entity
@Table(name = "greeting") // Specifies the table name in the DB
//greeting class

//UC-01 Plain java class(POJO)
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment primary key
    private Long id;

    private String message;

    //Default Constructor
    public Greeting() {}

    //parameterized constructor
    public Greeting(String message) {
        this.message = message;
    }

    //  Getters and Setters
    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}