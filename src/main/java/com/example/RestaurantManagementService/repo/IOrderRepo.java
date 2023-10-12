package com.example.RestaurantManagementService.repo;

import com.example.RestaurantManagementService.models.order.FoodOrder;
import com.example.RestaurantManagementService.models.user.User;
import com.example.RestaurantManagementService.services.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IOrderRepo extends JpaRepository<FoodOrder,Integer> {
    List<FoodOrder> findByUser(User user);
}
