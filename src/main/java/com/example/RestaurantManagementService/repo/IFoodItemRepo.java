package com.example.RestaurantManagementService.repo;

import com.example.RestaurantManagementService.models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodItemRepo extends JpaRepository<FoodItem,Integer> {
}
