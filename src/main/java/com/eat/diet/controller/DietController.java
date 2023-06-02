package com.eat.diet.controller;

import com.eat.diet.repo.model.*;
import com.eat.diet.service.DietService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DietController {

    List<Map<Food, Long>> breakfast;
    List<Map<Food, Long>> lunch;
    List<Map<Food, Long>> dinner;

    @Autowired
    private DietService dietService;
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("person", new Person());
        return "index";
    }

    //testing purpose
    @PostMapping("/api/food")
    public String addFood(@RequestBody Food food) {
        dietService.addFood(food);
        return "index";
    }

    @GetMapping("/mealPlan")
    public String mealPlan(Model model) {
        model.addAttribute("breakfast", breakfast.get(0));
        model.addAttribute("lunch", lunch.get(0));
        model.addAttribute("dinner", dinner.get(0));
        return "mealPlan";
    }


    @PostMapping("/diet")
    public String diet(@ModelAttribute Person person, BindingResult bindingResult, Model model) {
        int cal = dietService.calc(person);
        breakfast = dietService.pickMeal("breakfast", cal);
        lunch = dietService.pickMeal("lunch", cal);
        dinner = dietService.pickMeal("dinner", cal);
        return "redirect:/mealPlan";
    }
}