package com.example.RestaurantManagementService.services;

import com.example.RestaurantManagementService.models.FoodItem;
import com.example.RestaurantManagementService.models.UserAuthToken;
import com.example.RestaurantManagementService.models.dto.AuthInpDto;
import com.example.RestaurantManagementService.models.order.FoodOrder;
import com.example.RestaurantManagementService.models.order.OrderStatus;
import com.example.RestaurantManagementService.models.user.Role;
import com.example.RestaurantManagementService.repo.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;
    @Autowired
    FoodItemService foodItemService;
    @Autowired
    UserAuthTokenService userAuthTokenService;
    @Autowired
    UserService userService;

    public String placeOrder(AuthInpDto authInpDto, List<Integer> foodIds) {
        UserAuthToken userAuthToken=userAuthTokenService.getUserAuthToken(authInpDto);
        if(userAuthToken==null)return "wrong credentials";
        List<FoodItem> foodItems=foodItemService.findByIds(foodIds);
        if(foodItems==null||foodItems.size()==0)return "please add food items";
        FoodOrder foodOrder=new FoodOrder(foodItems,userAuthToken.getUser());
        orderRepo.save(foodOrder);
        return "order placed";
    }

    public String updateOrderStatus(AuthInpDto authInpDto, Integer orderId, OrderStatus orderStatus) {
        if(userService.isAdmin(authInpDto)){
            FoodOrder foodOrder=orderRepo.findById(orderId).orElse(null);
            if(foodOrder==null)return "wrong order id";
            foodOrder.setOrderStatus(orderStatus);
            orderRepo.save(foodOrder);
            return "updated successfully";

        }
        return "wrong input";
    }

    public List<FoodOrder> getOrders(AuthInpDto authInpDto) {
        UserAuthToken userAuthToken=userAuthTokenService.getUserAuthToken(authInpDto);
        if(userAuthToken == null)return null;
        return orderRepo.findByUser(userAuthToken.getUser());
    }
    private FoodOrder getOrderIfValid(AuthInpDto authInpDto, Integer orderId){
        UserAuthToken userAuthToken= userAuthTokenService.getUserAuthToken(authInpDto);
        if(userAuthToken==null)return null;
        FoodOrder order=orderRepo.findById(orderId).orElse(null);
        if(order==null)return null;
        if(order.getUser().equals(userAuthToken.getUser())||userAuthToken.getUser().getRole().equals(Role.ADMIN))
            return order;
        return null;
    }

    public String cancelOrder(AuthInpDto authInpDto, Integer orderId) {
        FoodOrder order=getOrderIfValid(authInpDto,orderId);
        if(order==null)return "wrong input";
        order.setOrderStatus(OrderStatus.CANCEL);
        orderRepo.save(order);
        return "order canceled successfully";
    }

    public FoodOrder getOrderById(AuthInpDto authInpDto, Integer orderId) {
        return getOrderIfValid(authInpDto,orderId);
    }
}
