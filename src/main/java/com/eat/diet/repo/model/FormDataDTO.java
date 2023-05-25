package com.eat.diet.repo.model;

import lombok.Data;

@Data
public class FormDataDTO {
    private Pref pref;
    private Gender gender;
    private Goal goal;
    private int weight;
    private int bodyFat;
    private ActivityLevel activityLevel;
}
