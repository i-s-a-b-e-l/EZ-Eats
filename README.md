
<h1 align="center">EZ Eats</h1>

<p align="center">The EZ Eats service provides users with an efficient diet planner that helps reach optimal well being by supporting a healthy lifestyle.</p>

## Table of Contents

- Built With
- Objects
- Diet Service
- Controllers
- Tests
- Static Files
- Templates

## Built With

- Java
  - Spring Boot (maven)
- HTML
  - thymeleaf (for data binding)
- CSS
  - bootstrap framework
- MongoDB (locally hosted)



## Objects
### Food
This class creates an object containing the characteristics of food.
- _String_ name (name of food)
- _int_ cal (calories)
- _String_ type (meal type)
- _boolean_ vegetarian (vegetarian friendly)
- _boolean_ vegan (vegan friendly)
- _boolean_ paleo (paleo friendly)
- _boolean_ keto (keto friendly)
- _String_ recipe (recipe url)
- _String_ img (file path for the food's image)

### Person
This class creates an object containing information about an individual including:

- _int_ weight
- _enum_ Gender (Female/Male)
- _enum_ ActivityLevel (Very Low, Low, Moderate, High, Very High)
- _int_ bodyFat (% body fat)
- _enum_ Pref (Vegetarian, Vegan, Paleo, Keto)
- _enum_ Goal (Lose Weight, Maintain Weight, Gain Weight)

### Food Repository
This interface holds the database queries.

### Food Data
This is the json file which holds the actual food objects (total 90 foods). The images of the foods are not valid (path for image is incorrect, image is a placeholder). Most recipe links are invalid as well.

## Diet Service

This class provides the logic to construct a healthy meal plan.

- _public int calc(Person person)_

  - This method takes a Person object as a parameter and calculates the daily intake of calories required using the individual's characteristics and goals
- _public List<List<Food>> planMeal (int calories, String meal)_
  
  - This method creates a _List_ containing two foods whose total calories amount is less than or equal to the daily calories requirement for breakfast, lunch, or dinner
      
- _public Optional<Food> getFoodLessThanCaloriesExcludingList(List<Food> foodList, int totalCalories)_

  - This method creates a list of all foods less than the total amount of calories required for either breakfast, lunch, or dinner
  
-  _public List<Food> addFoodsUntilCalories(List<Food> foodList, int caloriesPerMeal)_

    - This method adds foods to the foodList created in _planMeal(int calories, String meal)_ until the total amount of calories in Food List exceeds the daily calorie requirement for breakfast, lunch, or dinner
- _public int sumOfFoodsCalories(List<Food> foodList)_
  
  - This method returns the sum of all calories in foodList
- _public List<Map<Food, Long>> pickMeal(String meal, int totalCalories)_

  - This method creates a plan consisting of two meals with additional information
- _public List<Food> addSameFoodsUntilCalories(List<Food> foodList, int caloriesPerMeal)_

  - This method adds the same meals in the original plan to an _ArrayList_ until the calorie requirement is met
- _public static Map<Food, Long> countServings(List<Food> foods)_
  
  - This methods counts how many times meals are repeated using the _ArrayList_ from the _addSameFoodsUntilCalories_ method and returns a _Map<Food, Long>_ of servings for each meal.

## Controllers

### Diet Controller
This class handles the mapping for each path of the website, including GET and POST requests. It also handles the data binding to send the person data to Diet Service.

### CustomErrorController
This controller handles the error paths.

## Tests

### EZ Eats Application Tests
This class holds the generic Spring Boot application tests.

### Food Query Tests
This class holds all the tests associated with Diet Service. It outputs in the terminal.

## Static Files

### Placeholder
This image is not currently in use, but it is a placeholder for food images. Unfortunately none of the foods have real images.

### Logos
The other 2 image files are logos. One is a favicon while the other is the primary logo for EZ Eats.

## Templates

### Index
This is the home page of the website. It includes the main form needed to generate the meal plan.

### mealPlan
This is the page which contains the generated mealPlan. It can only be properly accessed after going through the form in index.

### How It Works
This page includes information on how the website works.

### About Us
This page includes information on the creators of the website.

### Error
This is the custom error page, which includes the status code to identify the error.

