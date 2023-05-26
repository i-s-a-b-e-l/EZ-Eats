package com.eat.diet.repo.model;

public enum Goal {
    LOSE("Lose Weight"),
    MAINTAIN("Maintain Weight"),
    GAIN("Gain Weight");

    private final String displayValue;

    private Goal(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
