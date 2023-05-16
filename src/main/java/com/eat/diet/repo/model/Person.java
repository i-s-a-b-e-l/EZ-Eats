package com.eat.diet.repo.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Person {
    private String name;
    private int weight;
    private Gender gender;
    private ActivityLevel activityLevel;
    private int bodyFat;
    private Pref pref; //Vegetarian, Vegan, or Non-vegetarian
    private Goal goal;

    public Person(String name, int weight, Gender gender, ActivityLevel activityLevel, int bodyFat, Pref pref, Goal goal){
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.bodyFat = bodyFat;
        this.pref = pref;
        this.goal = goal;
    }
}
