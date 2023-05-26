package com.eat.diet.repo.model;

public enum ActivityLevel {
    VERY_LOW("Very Low"),
    LOW("Low"),
    MODERATE("Moderate"),
    HIGH("High"),
    VERY_HIGH("Very High");
    private final String displayValue;

    private ActivityLevel(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
