package com.eat.diet.repo;

import com.eat.diet.repo.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {


    @Query("{type:'breakfast'}")
    List<Food> findAllBreakfast();

    @Query("{type:'breakfast', calories: {$lt: ?0 } }")
    List<Food> findAllBreakfastLessThanCalories(int calories);

    @Query("{type:'lunch'}")
    List<Food> findAllLunch();

    @Query("{type:'lunch', calories: {$lt: ?0 } }")
    List<Food> findAllLunchLessThanCalories(int calories);

    @Query("{type:'dinner'}")
    List<Food> findAllDinner();

    @Query("{type:'dinner', calories: {$lt: ?0 } }")
    List<Food> findAllDinnerLessThanCalories(int calories);

    @Query("{calories: { $lt: ?0 } }")
    List<Food> findAllFoodsLessThanCalories(int calories);

    @Query("{type:'breakfast'}, {vegetarian: true}, calories: {$lt: ?0 }")
    List<Food> findAllBreakfastLessThanCaloriesAndIsVegetarian(int calories);

    @Query("{type:'breakfast'}, {vegan: true}, calories: {$lt: ?0 }")
    List<Food> findAllBreakfastLessThanCaloriesAndIsVegan(int calories);

    @Query("{type:'breakfast'}, {paleo: true}, calories: {$lt: ?0 }")
    List<Food> findAllBreakfastLessThanCaloriesAndIsPaleo(int calories);

    @Query("{type:'breakfast'}, {keto: true}, calories: {$lt: ?0 }")
    List<Food> findAllBreakfastLessThanCaloriesAndIsKeto(int calories);

    @Query("{type:'lunch'}, {vegetarian: true}, calories: {$lt: ?0 }")
    List<Food> findAllLunchLessThanCaloriesAndIsVegetarian(int calories);

    @Query("{type:'lunch'}, {vegan: true}, calories: {$lt: ?0 }")
    List<Food> findAllLunchLessThanCaloriesAndIsVegan(int calories);

    @Query("{type:'lunch'}, {paleo: true}, calories: {$lt: ?0 }")
    List<Food> findAllLunchLessThanCaloriesAndIsPaleo(int calories);

    @Query("{type:'lunch'}, {keto: true}, calories: {$lt: ?0 }")
    List<Food> findAllLunchLessThanCaloriesAndIsKeto(int calories);


    @Query("{type:'dinner'}, {vegetarian: true}, calories: {$lt: ?0 }")
    List<Food> findAllDinnerLessThanCaloriesAndIsVegetarian(int calories);

    @Query("{type:'dinner'}, {vegan: true}, calories: {$lt: ?0 }")
    List<Food> findAllDinnerLessThanCaloriesAndIsVegan(int calories);

    @Query("{type:'dinner'}, {paleo: true}, calories: {$lt: ?0 }")
    List<Food> findAllDinnerLessThanCaloriesAndIsPaleo(int calories);

    @Query("{type:'dinner'}, {keto: true}, calories: {$lt: ?0 }")
    List<Food> findAllDinnerLessThanCaloriesAndIsKeto(int calories);


}