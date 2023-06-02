package com.eat.diet.service;

import com.eat.diet.repo.FoodRepository;
import com.eat.diet.repo.model.*;
//import jdk.internal.loader.BuiltinClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
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
        int totalCalories;
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
        if (person.getActivityLevel() == ActivityLevel.VERY_LOW) {
            totalCalories = (int) (bmr * 1.3);
        } else if (person.getActivityLevel() == ActivityLevel.LOW) {
            totalCalories = (int) (bmr * 1.55);
        } else if (person.getActivityLevel() == ActivityLevel.MODERATE) {
            totalCalories = (int) (bmr * 1.65);
        } else if (person.getActivityLevel() == ActivityLevel.HIGH) {
            totalCalories = (int) (bmr * 1.8);
        } else {
            totalCalories = (int) (bmr * 2.0);
        }

        // adjust cal amt based on goal
        if (person.getGoal() == Goal.GAIN) {
            totalCalories += 400;
        } else if (person.getGoal() == Goal.LOSE) {
            totalCalories -= 500;
        }
        return totalCalories;
    }

    /**
     * Find TWO Foods whose sum of calories will be less than Calories.
     *
     * @param meal - which meal to plan for
     * @return - list of combinations of foods whose sum is less than the calorie count
     */
    public List<List<Food>> planMeal(String meal, int totalCalories, Pref pref) {
        List<List<Food>> foods = new ArrayList<>();
        int perMeal = totalCalories / 3;

        try {
            //Retrieve all foods less than <calories>
            List<Food> allMealsLessThanCalories;
            if(pref == Pref.NONE) {
                if (meal.equals("breakfast")) {
                    allMealsLessThanCalories = foodRepository.findAllBreakfastLessThanCalories(perMeal);
                } else if (meal.equals("lunch")) {
                    allMealsLessThanCalories = foodRepository.findAllLunchLessThanCalories(perMeal);
                } else {
                    allMealsLessThanCalories = foodRepository.findAllDinnerLessThanCalories(perMeal);
                }


                // Filter all foods to be combination less than calories.
                for (Food f1 : allMealsLessThanCalories) {
                    //List returns all foods whose sum with f1.calories <= calories
                    List<Food> comboFoodsLessThanCalories = allMealsLessThanCalories
                            .stream()
                            .filter(f2 -> !f2.getName().equals(f1.getName())) //Exclude the comparing food.
                            .filter(f2 -> f1.getCalories() + f2.getCalories() <= totalCalories) // Find if sum is less than calories
                            .collect(Collectors.toList());

                    comboFoodsLessThanCalories.stream().forEach(f -> {
                                foods.add(List.of(f, f1));
                            }

                    );
                }
            }

            else if(pref == Pref.VEGAN){

                //Retrieve all foods less than <calories> and is VEGAN
                if (meal.equals("breakfast")) {
                    allMealsLessThanCalories = foodRepository.findAllBreakfastLessThanCaloriesAndIsVegan(perMeal);
                } else if (meal.equals("lunch")) {
                    allMealsLessThanCalories = foodRepository.findAllLunchLessThanCaloriesAndIsVegan(perMeal);
                } else {
                    allMealsLessThanCalories = foodRepository.findAllDinnerLessThanCaloriesAndIsVegan(perMeal);
                }

                // Filter all foods to be combination less than calories given VEGAN pref.
                for (Food f1 : allMealsLessThanCalories) {
                    //List returns all foods whose sum with f1.calories <= calories
                    List<Food> comboFoodsLessThanCalories = allMealsLessThanCalories
                            .stream()
                            .filter(f2 -> !f2.getName().equals(f1.getName())) //Exclude the comparing food.
                            .filter(f2 -> f1.getCalories() + f2.getCalories() <= totalCalories) // Find if sum is less than calories
                            .collect(Collectors.toList());

                    comboFoodsLessThanCalories.stream().forEach(f -> {
                                foods.add(List.of(f, f1));
                            }

                    );
                }
            }


        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        // testing
        log.info("foods size {}", foods.size());
//        log.info("foods first item size {}",foods.get(0).size());
//        log.info("foods first item list {}",foods.get(0));
//        log.info("foods ten item size {}",foods.get(10).size());
//        log.info("foods ten item list {}",foods.get(10));

        return foods;
    }

    /**
     * Return first Food that is less than calories comparing the foodList
     *
     * @param foodList - list of foods
     * @return - optional
     */
    public Optional<Food> getFoodLessThanCaloriesExcludingList(List<Food> foodList, int totalCalories) {

        List<Food> foods = new ArrayList<>();
        foods.addAll(foodList);

        try {
            Integer currentSum = foodList.stream().map(l -> l.getCalories()).collect(Collectors.summingInt(Integer::intValue));
            List<String> foodNamesList = foodList.stream().map(l -> l.getName()).collect(Collectors.toList());
            // Retrieve all foods less than <calories-currentSum>
            List<Food> allBreakfastLessThanCalories = foodRepository.findAllBreakfastLessThanCalories(totalCalories / 3 - currentSum);

            return allBreakfastLessThanCalories.stream().filter(l -> !foodNamesList.contains(l.getName())).findFirst();

        } catch (Exception e) {
            log.info(e.getMessage(), e);
            // throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    /**
     * keeps adding foods to the <foodList> until <caloriesPerMeal> is reached
     *
     * @param foodList        - list of foods
     * @param caloriesPerMeal - add foods to each meal based on calories per meal
     * @return - a new list of foods
     */
    public List<Food> addFoodsUntilCalories(List<Food> foodList, int caloriesPerMeal) {
        List<Food> responseList = new ArrayList<>();
        responseList.addAll(foodList);
        while (sumOfFoodsCalories(responseList) < caloriesPerMeal && getFoodLessThanCaloriesExcludingList(responseList, caloriesPerMeal).isPresent()) {
            responseList.add(getFoodLessThanCaloriesExcludingList(responseList, caloriesPerMeal).get());
        }
        return responseList;
    }

    public List<Food> addSameFoodsUntilCalories(List<Food> foodList, int caloriesPerMeal) {
        List<Food> responseList = new ArrayList<>();
        responseList.addAll(foodList);

        while (sumOfFoodsCalories(responseList) < caloriesPerMeal) {
            responseList.add(foodList.get((int) (Math.random() * foodList.size())));
        }
        return responseList;
    }

    /**
     * returns the total calories of all foods in <foodList>
     *
     * @param foodList - list of foods
     * @return int sum of foods
     */
    public int sumOfFoodsCalories(List<Food> foodList) {
        return foodList.stream().map(l -> l.getCalories()).collect(Collectors.summingInt(Integer::intValue));
    }

    /**
     * method that will be called from diet controller
     * picks out a random meal from the generated possibilities & formats it to be html friendly
     *
     * @param meal - which type of food to pick
     * @return - randomly picked food combo of size 2
     */
    public List<Map<Food, Long>> pickMeal(String meal, int totalCalories, Pref pref) {
        List<List<Food>> lists = planMeal(meal, totalCalories, pref);
        for (int i = 0; i < lists.size(); i++) {
            lists.set(i, addSameFoodsUntilCalories(lists.get(i), totalCalories / 3));
        }
        int index = (int) (Math.random() * lists.size());
        List<Food> choice = lists.get(index);
        List<Map<Food, Long>> plan = new ArrayList<>();
        for (int i = 0; i < choice.size(); i++) {
            plan.add(i, countServings(choice));
        }
        return plan;
    }

    public static Map<Food, Long> countServings(List<Food> foods) {
        return foods.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}