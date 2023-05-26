package com.eat.diet.controller;

import com.eat.diet.repo.model.*;
import com.eat.diet.service.DietService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DietController {
    @Autowired
    private DietService dietService;
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("Person", new Person());
        return "index";
    }

    //testing purpose
    @PostMapping("/api/food")
    public String addFood(@RequestBody Food food) {
        dietService.addFood(food);
        return "index";
    }

    @GetMapping("mealPlan")
    public String mealPlan() {
        return "mealPlan";
    }


    @PostMapping("/diet")
    public String diet(@ModelAttribute Person person, BindingResult bindingResult, Model model) {
        //Person person = new Person(formData.getWeight(), formData.getGender(), formData.getActivityLevel(), formData.getBodyFat(), formData.getPref(), formData.getGoal());
        int cal = dietService.calc(person);
        List<Map<String, String>> breakfast = dietService.pickMeal("breakfast", cal);
        List<Map<String, String>> lunch = dietService.pickMeal("lunch", cal);
        List<Map<String, String>> dinner = dietService.pickMeal("dinner", cal);
        //model.addAttribute("breakfastList", breakfast);
        model.addAttribute("Person", person);
        //TODO @Zoey: render the above in the html page
        return "index";
    }
}