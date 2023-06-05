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
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Person person = new Person(200, Gender.MALE, ActivityLevel.HIGH, 10, Pref.VEGAN, Goal.GAIN);
        int cal = dietService.calc(person);
        Pref pref = person.getPref();
        System.out.println(pref);
        List<List<Food>> lists = dietService.planMeal("breakfast", cal, pref );

        lists.forEach(l -> {
            List<Food> foods = dietService.addSameFoodsUntilCalories(l, 1000);
            Map<Food, Long> servingCounts = dietService.countServings(foods);
            servingCounts.entrySet().forEach(r1 -> System.out.println("Item: " + r1.getKey().getName()  + ", Calories:" + r1.getKey().getCalories() +", Servings :" + r1.getValue()));
            System.out.println("-----------------");
        });


        log.info("Ending Test.. ");
    }

    @Test
    void testPickMeal() {
        log.info("Starting Test... ");

        int cal = dietService.calc(new Person(200, Gender.MALE, ActivityLevel.HIGH, 10, Pref.NONE, Goal.GAIN));
        System.out.println("calories: " + cal);
        List<Map<Food, Long>> breakfast = dietService.pickMeal("lunch", cal, Pref.VEGAN);
        System.out.println("breakfast size: " + breakfast.size());

        breakfast.get(0).entrySet().forEach(item -> {
                System.out.println("name: " + item.getKey().getName());
                System.out.println("calories: " + item.getKey().getCalories());
                System.out.println("servings: " + item.getValue());
            });
        log.info("Test Complete");
    }
}
