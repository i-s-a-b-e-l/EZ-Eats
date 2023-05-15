package com.eat.diet.repo.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Person {
    private String name;
    private int weight;
    private Gender gender;
    private String activityLevel;

    private int bodyFat;
    private String pref; //Vegetarian, Vegan, or Non-vegetarian
    private String goal;




    public Person(String name, int weight, Gender gender, String activityLevel, int bodyFat, String pref, String goal){
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.bodyFat = bodyFat;
        this.pref = pref;
        this.goal = goal;
    }






}
