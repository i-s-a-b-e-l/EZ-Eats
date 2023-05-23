package com.eat.diet.service;

import com.eat.diet.repo.FoodRepository;
import com.eat.diet.repo.model.*;
//import jdk.internal.loader.BuiltinClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DietService {

    @Autowired
    private FoodRepository foodRepository;

    //testing purpose
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    public int calc(Person person) {
        int calAmount;
        double kg = person.getWeight() / 2.2;
        double bmr;

        //get bmr based on gender
        if (person.getGender() == Gender.FEMALE) {
            bmr = kg * 0.9 * 24;
            if (person.getBodyFat() > 38) {
                bmr *= .85;
            } else if (person.getBodyFat() > 28) {
                bmr *= 0.9;
            } else if (person.getBodyFat() > 18) {
                bmr *= 0.95;
            }
        } else {
            bmr = kg * 24;
            if (person.getBodyFat() > 28) {
                bmr *= .85;
            } else if (person.getBodyFat() > 20) {
                bmr *= 0.90;
            } else if (person.getBodyFat() > 14) {
                bmr *= 0.95;
            }
        }

        // get cal amt based on activity lvl
        if (person.getActivityLevel() == ActivityLevel.VERYLOW) {
            calAmount = (int) (bmr * 1.3);
        } else if (person.getActivityLevel() == ActivityLevel.LOW) {
            calAmount = (int) (bmr * 1.55);
        } else if (person.getActivityLevel() == ActivityLevel.MODERATE) {
            calAmount = (int) (bmr * 1.65);
        } else if (person.getActivityLevel() == ActivityLevel.HIGH) {
            calAmount = (int) (bmr * 1.8);
        } else {
            calAmount = (int) (bmr * 2.0);
        }

        // adjust cal amt based on goal
        if (person.getGoal() == Goal.MAINTAIN) {
            return calAmount;
        } else if (person.getGoal() == Goal.LOSE) {
            return calAmount - 500;
        } else {
            return calAmount + 400;
        }
    }

    /**
     * TODO Implement for planMealForCalorie
     * <p>
     * Find TWO Foods whose sum of calories will be less than Calories.
     *
     * @param calories
     * @return
     */
    public List<List<Food>> planMeal (int calories, String meal) {
        List<List<Food>> foods = new ArrayList<>();
        int perMeal = calories / 3;

        try {
            //Retrieve all foods less than <calories>
            List<Food> allMealsLessThanCalories;
            if (meal.equals("breakfast")) {
                allMealsLessThanCalories = foodRepository.findAllBreakfastLessThanCalories(perMeal);
            }
            else if (meal.equals("lunch")) {
                allMealsLessThanCalories = foodRepository.findAllLunchLessThanCalories(perMeal);
            }
            else {
                allMealsLessThanCalories = foodRepository.findAllDinnerLessThanCalories(perMeal);
            }

            // Filter all foods to be combination less than calories.
            for (Food f1 : allMealsLessThanCalories) {
                //List returns all foods whose sum with f1.calories <= calories
                List<Food> comboFoodsLessThanCalories = allMealsLessThanCalories
                        .stream()
                        .filter(f2 -> f2.getName() != f1.getName()) //Exclude the comparing food.
                        .filter(f2 -> f1.getCalories() + f2.getCalories() <= calories) // Find if sum is less than calories
                        .collect(Collectors.toList());

                comboFoodsLessThanCalories.stream().forEach(f -> {
                            foods.add(List.of(f, f1));
                        }

                );
            }


        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        log.info("foods size {}",foods.size());
        log.info("foods first item size {}",foods.get(0).size());
        log.info("foods first item list {}",foods.get(0));

        log.info("foods ten item size {}",foods.get(10).size());
        log.info("foods ten item list {}",foods.get(10));
        return foods;
    }

    /**
     * Return first Food that is less than calories comparing the foodList
     *
     * @param foodList
     * @param calories
     * @return
     */
    public Optional<Food> getFoodLessThanCaloriesExcludingList(List<Food> foodList, int calories) {

        List<Food> foods = new ArrayList<>();
        foods.addAll(foodList);

        try {
            Integer currentSum = foodList.stream().map(l -> l.getCalories()).collect(Collectors.summingInt(Integer::intValue));
            List<String> foodNamesList = foodList.stream().map(l -> l.getName()).collect(Collectors.toList());
            // Retrieve all foods less than <calories-currentSum>
            List<Food> allBreakfastLessThanCalories = foodRepository.findAllBreakfastLessThanCalories(calories - currentSum);

            return allBreakfastLessThanCalories.stream().filter(l -> !foodNamesList.contains(l.getName())).findFirst();

        } catch (Exception e) {
            log.info(e.getMessage(), e);
            // throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Food> addFoodsUntilCalories(List<Food> foodList, int calories) {
        List<Food> responseList = new ArrayList<>();
        responseList.addAll(foodList);
        while (sumOfFoodsCalories(responseList) < calories && getFoodLessThanCaloriesExcludingList(responseList, calories).isPresent()) {
            responseList.add(getFoodLessThanCaloriesExcludingList(responseList, calories).get());
        }
        return responseList;
    }

    public int sumOfFoodsCalories(List<Food> foodList) {
        return foodList.stream().map(l -> l.getCalories()).collect(Collectors.summingInt(Integer::intValue));
    }
}