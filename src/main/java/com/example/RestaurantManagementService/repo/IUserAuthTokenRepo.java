package com.example.RestaurantManagementService.repo;

import com.example.RestaurantManagementService.models.UserAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAuthTokenRepo extends JpaRepository<UserAuthToken,Integer> {
    UserAuthToken findFirstByValue(String authInpDto);
}
