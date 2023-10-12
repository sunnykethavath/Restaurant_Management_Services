package com.example.RestaurantManagementService.controller;

import com.example.RestaurantManagementService.models.FoodItem;
import com.example.RestaurantManagementService.models.dto.AuthInpDto;
import com.example.RestaurantManagementService.models.dto.FoodDto;
import com.example.RestaurantManagementService.services.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/food")
    public String addFood(@RequestBody FoodDto foodDto){
        return foodItemService.addFood(foodDto);
    }

    @GetMapping("/food/all")
    public List<FoodItem> getAll(){
        return foodItemService.getAll();
    }
    @PutMapping("/food/id/{id}")
    public String updateFoodById(@RequestBody AuthInpDto authInpDto, @PathVariable(name = "id") Integer foodItemId, @RequestParam(required = false) String title, @RequestParam(required = false) String description){
        return foodItemService.updateFoodById(authInpDto,foodItemId,title,description);
    }
}
