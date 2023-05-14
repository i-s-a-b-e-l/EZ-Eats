package com.eat.diet.service;

import com.eat.diet.repo.FoodRepository;
import com.eat.diet.repo.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietService {

    @Autowired
    private FoodRepository foodRepository;



    public Food createFood(String name){
        Food food = new Food();
        food.setName(name);
        return foodRepository.save(food);

    }

    public int calc(String goal, int weight, String gender, String activityLevel, int bodyFat){
        if(goal.equals("Maintain Weight")){
            double kg = weight/2.2;
            if(gender.equals("Female")){
                double BMR = kg * 0.9 * 24;
                if(bodyFat <= 18){
                    BMR = BMR * 1.0;
                }
                else if(bodyFat <= 28){
                    BMR = BMR * 0.95;
                }
                else if(bodyFat <= 38){
                    BMR = BMR * 0.90;
                }
                else{
                    BMR = BMR * 0.85;
                }
                int calAmount = 0;
                if(activityLevel.equals("Very Low")){
                    calAmount = (int)(BMR * 1.3);
                }
                if(activityLevel.equals("Low")){
                    calAmount = (int)(BMR * 1.55);
                }
                if(activityLevel.equals("Moderate")){
                    calAmount = (int)(BMR * 1.65);
                }
                if(activityLevel.equals("High")){
                    calAmount = (int)(BMR * 1.8);
                }
                if(activityLevel.equals("Very High")){
                    calAmount = (int)(BMR * 2.0);
                }
                return calAmount;

            }
            if(gender.equals("Male")){
                double BMR = kg * 24;


            }

        }

        if(goal.equals("Lose Weight")){

        }

        if(goal.equals("Gain Weight")){

        }
    }


}
