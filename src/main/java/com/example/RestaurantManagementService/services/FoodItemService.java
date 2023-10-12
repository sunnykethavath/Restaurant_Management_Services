package com.example.RestaurantManagementService.services;

import com.example.RestaurantManagementService.models.FoodItem;
import com.example.RestaurantManagementService.models.UserAuthToken;
import com.example.RestaurantManagementService.models.dto.AuthInpDto;
import com.example.RestaurantManagementService.models.dto.FoodDto;
import com.example.RestaurantManagementService.models.user.Role;
import com.example.RestaurantManagementService.repo.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class FoodItemService {
    @Autowired
    IFoodItemRepo foodItemRepo;
    @Autowired
    UserAuthTokenService userAuthTokenService;
    private boolean isNotAValidUser(AuthInpDto authInpDto){
        UserAuthToken userAuthToken=userAuthTokenService.getUserAuthToken(authInpDto);
        if(userAuthToken==null)return true;
        return !userAuthToken.getUser().getRole().equals(Role.ADMIN);
    }

    public String addFood(FoodDto foodDto) {
        if(isNotAValidUser(foodDto.getAuthInpDto()))return "don't have privileges";
        FoodItem foodItem=new FoodItem(foodDto.getTitle(),foodDto.getDescription(),foodDto.getImageUrl());
        foodItemRepo.save(foodItem);
        return "added successfully";
    }

    public List<FoodItem> getAll() {
        return foodItemRepo.findAll();
    }


    private boolean isNullOrEmpty(String s){
        return s!=null&&!s.isEmpty();
    }

    private void updateIfValid(String s, Predicate<String> valid, Consumer<String> apply){
        if(valid.test(s)){
            apply.accept(s);
        }
    }

    public String updateFoodById(AuthInpDto authInpDto, Integer foodItemId, String title, String description) {
        if(isNullOrEmpty(title)&&isNullOrEmpty(description)) return "nothing to update";
        if(isNotAValidUser(authInpDto))return "don't have privileges";
        FoodItem foodItem=foodItemRepo.findById(foodItemId).orElseThrow();
        updateIfValid(title,this::isNullOrEmpty,foodItem::setTitle);
        updateIfValid(description,this::isNullOrEmpty,foodItem::setDescription);
        foodItemRepo.save(foodItem);
        return "updated successfully";
    }

    public List<FoodItem> findByIds(List<Integer> foodIds) {
        return foodItemRepo.findAllById(foodIds);
    }
}
