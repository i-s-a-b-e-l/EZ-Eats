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
}
