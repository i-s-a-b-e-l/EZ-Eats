package com.eat.diet.service;

import com.eat.diet.repo.FoodRepository;
import com.eat.diet.repo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DietService {
    @Autowired
    private FoodRepository foodRepository;

    public Food addFood(Food food){
        return foodRepository.save(food);
    }

    public int calc(Person person){
        int calAmount;
        double kg = person.getWeight() / 2.2;
        double bmr;

        //get bmr based on gender
        if(person.getGender() == Gender.FEMALE) {
            bmr = kg * 0.9 * 24;
            if (person.getBodyFat() > 38) {
                bmr *= .85;
            }
            else if (person.getBodyFat() > 28) {
                bmr *= 0.9;
            }
            else if (person.getBodyFat() > 18) {
                bmr *= 0.95;
            }
        }
        else {
            bmr = kg * 24;
            if(person.getBodyFat() > 28){
                bmr *= .85;
            }
            else if(person.getBodyFat() > 20){
                bmr *= 0.90;
            }
            else if(person.getBodyFat() > 14){
                bmr *= 0.95;
            }
        }

        // get cal amt based on activity lvl
        if(person.getActivityLevel() == ActivityLevel.VERYLOW){
            calAmount = (int)(bmr * 1.3);
        }
        else if(person.getActivityLevel() == ActivityLevel.LOW){
            calAmount = (int)(bmr * 1.55);
        }
        else if(person.getActivityLevel() == ActivityLevel.MODERATE){
            calAmount = (int)(bmr * 1.65);
        }
        else if(person.getActivityLevel() == ActivityLevel.HIGH){
            calAmount = (int)(bmr * 1.8);
        }
        else {
            calAmount = (int)(bmr * 2.0);
        }

        // adjust cal amt based on goal
        if(person.getGoal() == Goal.MAINTAIN) {
            return calAmount;
        }
        else if(person.getGoal() == Goal.LOSE){
            return calAmount - 500;
        }
        else {
            return calAmount + 400;
        }
    }

    public Map<String, Food> foodSuggestion(int cal){
        //TODO @Mahima: learn maps -> take cal as input, return a map -> food list, {breakfast, {food list}}
        return null;
    }

}
