package com.eat.diet.service;

import com.eat.diet.repo.FoodRepository;
import com.eat.diet.repo.model.Food;
import com.eat.diet.repo.model.Person;
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
        int calAmount = 0;
            double kg = person.getWeight()/2.2;
            if(person.getGender().equals("Female")){
                double BMR = kg * 0.9 * 24;
                if(person.getBodyFat() <= 18){
                    BMR = BMR * 1.0;
                }
                else if(person.getBodyFat() <= 28){
                    BMR = BMR * 0.95;
                }
                else if(person.getBodyFat() <= 38){
                    BMR = BMR * 0.90;
                }
                else{
                    BMR = BMR * 0.85;
                }

                if(person.getActivityLevel().equals("Very Low")){
                    calAmount = (int)(BMR * 1.3);
                }
                if(person.getActivityLevel().equals("Low")){
                    calAmount = (int)(BMR * 1.55);
                }
                if(person.getActivityLevel().equals("Moderate")){
                    calAmount = (int)(BMR * 1.65);
                }
                if(person.getActivityLevel().equals("High")){
                    calAmount = (int)(BMR * 1.8);
                }
                if(person.getActivityLevel().equals("Very High")) {
                    calAmount = (int) (BMR * 2.0);
                }

            }
            if(person.getGender().equals("Male")){
                double BMR = kg * 24;
                if(person.getBodyFat() <= 14){
                    BMR = BMR * 1.0;
                }
                else if(person.getBodyFat() <= 20){
                    BMR = BMR * 0.95;
                }
                else if(person.getBodyFat() <= 28){
                    BMR = BMR * 0.90;
                }
                else{
                    BMR = BMR * 0.85;
                }
                if(person.getActivityLevel().equals("Very Low")){
                    calAmount = (int)(BMR * 1.3);
                }
                if(person.getActivityLevel().equals("Low")){
                    calAmount = (int)(BMR * 1.55);
                }
                if(person.getActivityLevel().equals("Moderate")){
                    calAmount = (int)(BMR * 1.65);
                }
                if(person.getActivityLevel().equals("High")){
                    calAmount = (int)(BMR * 1.8);
                }
                if(person.getActivityLevel().equals("Very High")) {
                    calAmount = (int) (BMR * 2.0);
                }



            }
        if(person.getGoal().equals("Maintain Weight")) {
            return calAmount;
        }



        if(person.getGoal().equals("Lose Weight")){
            return calAmount - 500;


        }

        if(person.getGoal().equals("Gain Weight")){
            return calAmount + 400;

        }
        if(calAmount < 1200){
            return 1200;
        }

        return calAmount;

    }




    public Map<String, Food> foodSuggestion(int cal){
        //TODO @Mahima: learn maps -> take cal as input, return a map -> food list, {breakfast, {food list}}
        return null;
    }

}
