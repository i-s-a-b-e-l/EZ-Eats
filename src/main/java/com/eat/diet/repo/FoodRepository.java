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

    @Query("{type:'breakfast'}, {vegetarian: true}")
    List<Food> findAllBreakfastLessThanCaloriesAndIsVegetarian(int calories);

    @Query("{type:'breakfast'}, {vegan: true}")
    List<Food> findAllBreakfastLessThanCaloriesAndIsVegan(int calories);

    @Query("{type:'breakfast'}, {paleo: true}")
    List<Food> findAllBreakfastLessThanCaloriesAndIsPaleo(int calories);

    @Query("{type:'breakfast'}, {keto: true}")
    List<Food> findAllBreakfastLessThanCaloriesAndIsKeto(int calories);

    @Query("{type:'lunch'}, {vegetarian: true}")
    List<Food> findAllLunchLessThanCaloriesAndIsVegetarian(int calories);

    @Query("{type:'lunch'}, {vegan: true}")
    List<Food> findAllLunchLessThanCaloriesAndIsVegan(int calories);

    @Query("{type:'lunch'}, {paleo: true}")
    List<Food> findAllLunchLessThanCaloriesAndIsPaleo(int calories);

    @Query("{type:'lunch'}, {keto: true}")
    List<Food> findAllLunchLessThanCaloriesAndIsKeto(int calories);


    @Query("{type:'dinner'}, {vegetarian: true}")
    List<Food> findAllDinnerLessThanCaloriesAndIsVegetarian(int calories);

    @Query("{type:'dinner'}, {vegan: true}")
    List<Food> findAllDinnerLessThanCaloriesAndIsVegan(int calories);

    @Query("{type:'dinner'}, {paleo: true}")
    List<Food> findAllDinnerLessThanCaloriesAndIsPaleo(int calories);

    @Query("{type:'dinner'}, {keto: true}")
    List<Food> findAllDinnerLessThanCaloriesAndIsKeto(int calories);


}