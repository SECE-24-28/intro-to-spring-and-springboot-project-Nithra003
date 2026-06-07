package com.eduhub.eduhub_backend.component;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private int id;

    private String firstName;
    private String lastName;

    public Student(){

    }

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

