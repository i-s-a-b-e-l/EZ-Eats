
<h1 align="center">EZ Eats</h1>

<p align="center">The EZ Eats service provides users with an efficient diet planner that helps reach optimal well being by supporting a healthy lifestyle.</p>

## Table of Contents

- Built With
- Classes
- Creators
- Add other stuff

## Built With

- Java
- HTML
- CSS



## Classes
### Food
This class creates an object containing the characteristics of a food.

### Person
This class creates an object containing information about an individual including weight, gender, activity level, body fat percentage, and food preferences.

### Diet Service

This class provides services required for creating a healthy meal plan
 
- **Methods**

    - _public int calc(Person person){}_

      - This method takes a Person object as a parameter and calculates the daily intake of calories required using the individual's characteristics and goals
    - _public List<List<Food>> planMeal (int calories, String meal){}_
  
      - This method creates a _List_ containing two foods whose total calories amount is less than or equal to the daily calories requirement for breakfast, lunch, or dinner
      
  - _public Optional<Food> getFoodLessThanCaloriesExcludingList(List<Food> foodList, int totalCalories){}_

    - This method creates a list of all foods less than the total amount of calories required for either breakfast, lunch, or dinner
  
  -  _public List<Food> addFoodsUntilCalories(List<Food> foodList, int caloriesPerMeal){}_

      - This method adds foods to the foodList created in _planMeal(int calories, String meal)_ until the total amount of calories in Food List exceeds the daily calorie requirement for breakfast, lunch, or dinner
  - _public int sumOfFoodsCalories(List<Food> foodList)_
  
    - This method returns the sum of all calories in foodList
  - _public List<Map<String, String>> pickMeal(String meal, int totalCalories){}_
  



