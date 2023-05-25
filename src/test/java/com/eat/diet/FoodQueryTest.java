package com.eat.diet;

import com.eat.diet.repo.FoodRepository;
import com.eat.diet.repo.model.*;
import com.eat.diet.service.DietService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class FoodQueryTest {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    DietService dietService;

    @Test
    void testFoodQuery() {
        log.info("Starting Test.. ");

        Assert.notNull(foodRepository, "FoodRepository  is null");

        List<Food> allBreakfast = foodRepository.findAllBreakfast();

        Assert.isTrue(allBreakfast.size() > 0, "Breakfast food not found");

        log.info("Ending Test.. ");
    }

    @Test
    void testFoodQueryByCalories() {
        log.info("Starting Test.. ");

        List<Food> allBreakfast = foodRepository.findAllFoodsLessThanCalories(500);

        Assert.isTrue(allBreakfast.size() > 0, "Less than calories food not found");

        log.info("Ending Test.. ");
    }

    @Test
    void testPlanMeal() {
        log.info("Starting Test.. ");

        Assert.notNull(dietService, "DietService not found");

        int cal = dietService.calc(new Person(200, Gender.MALE, ActivityLevel.HIGH, 10, Pref.NONE, Goal.GAIN));
        List<List<Food>> lists = dietService.planMeal("breakfast", cal);

        lists.forEach(l -> {
            List<Food> foods = dietService.addFoodsUntilCalories(l, 333);
            foods.forEach(l1 -> System.out.println(l1.getName() + ": " + l1.getCalories()) );
            System.out.println("-----------------");
        });


        log.info("Ending Test.. ");
    }

    @Test
    void testPickMeal() {
        log.info("Starting Test... ");

        int cal = dietService.calc(new Person(200, Gender.MALE, ActivityLevel.HIGH, 10, Pref.NONE, Goal.GAIN));
        System.out.println("calories: " + cal);
        List<Map<String, String>> breakfast = dietService.pickMeal("lunch", cal);

        breakfast.forEach(print -> {
            System.out.println("{\nname: " + print.get("name"));
            System.out.println("cal: " + print.get("calories"));
            System.out.println("vegetarian: " + print.get("vegetarian"));
            System.out.println("vegan: " + print.get("vegan"));
            System.out.println("paleo: " + print.get("paleo"));
            System.out.println("keto: " + print.get("keto") + "\n}");
        });

        log.info("Test Complete");
    }
}
