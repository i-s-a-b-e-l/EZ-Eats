package com.eat.diet.controller;

import com.eat.diet.repo.model.*;
import com.eat.diet.service.DietService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
        return "index";
    }

    //testing purpose
    @PostMapping("/api/food")
    public String addFood(@RequestBody Food food) {
        dietService.addFood(food);
        return "index";
    }

    @PostMapping("/plan")
    public String diet(@ModelAttribute("formData") FormDataDTO formData, Model model) {
        if (formData.getWeight() != 0 || formData.getGender() != null || formData.getActivityLevel() != null || formData.getBodyFat() != 0 || formData.getPref() != null || formData.getGoal() != null) {
            model.addAttribute("message", "The form is incomplete. Cannot calculate necessary calories to plan meals.");
        }
        Person person = new Person(formData.getWeight(), formData.getGender(), formData.getActivityLevel(), formData.getBodyFat(), formData.getPref(), formData.getGoal());
        int cal = dietService.calc(person);
        List<Map<String, String>> breakfast = dietService.pickMeal("breakfast", cal);
        List<Map<String, String>> lunch = dietService.pickMeal("lunch", cal);
        List<Map<String, String>> dinner = dietService.pickMeal("dinner", cal);
        model.addAttribute("breakfastList", breakfast);

        //TODO @Zoey: render the above in the html page
        return "mealPlan";
    }
}
