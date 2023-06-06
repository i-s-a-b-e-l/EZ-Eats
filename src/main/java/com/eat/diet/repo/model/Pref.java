package com.eat.diet.repo.model;

public enum Pref {
    NONE("No Preference"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    PALEO("Paleo"),
    KETO("Keto");

    private final String displayValue;

    private Pref(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
