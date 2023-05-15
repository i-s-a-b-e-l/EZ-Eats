package com.eat.diet.repo.model;

import lombok.Data;

@Data
public class Person {
    private String name;
    private int weight;
    private Gender gender;
    private String activityLevel;
    private int bodyFat;

    //TODO @Mahima: make a constructor for a person
}
