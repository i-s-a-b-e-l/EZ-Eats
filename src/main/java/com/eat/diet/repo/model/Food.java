package com.eat.diet.repo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int calories;
    private int serving;
    private String type; //Breakfast, Lunch, or Dinner

    public Food(){
        name = " ";
        calories = 0;
        serving = 0;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }

    public int getCalories(){
        return calories;
    }

    public void setServing(int serving){
        this.serving = serving;
    }

    public int getServing(){
        return serving;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }




}

