package com.example.jpaspring.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class Person {

   // private Long personId;
    private String firstName;
    private String lastName;


    public Person() {

    }
}
