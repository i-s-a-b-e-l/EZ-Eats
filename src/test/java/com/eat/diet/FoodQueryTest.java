package com.eat.diet;

import com.eat.diet.repo.FoodRepository;
import com.eat.diet.repo.model.Food;
import com.eat.diet.service.DietService;
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
    void testPlanMealForBreakfast() {
        log.info("Starting Test.. ");


        Assert.notNull(dietService, "DietService not found");

        List<List<Food>> lists = dietService.planMealForBreakfast(400);


        lists.forEach(l -> {
            List<Food> foods = dietService.addFoodsUntilCalories(l, 400);
            foods.forEach(l1 -> System.out.println(l1.getName() + ":" + l1.getCalories()) );
            System.out.println("-----------------");
        });


        log.info("Ending Test.. ");
    }
}
