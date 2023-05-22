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

    @Query("{calories: { $lt: ?0 }  }")
    List<Food> findAllFoodsLessThanCalories(int calories);

}