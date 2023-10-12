package com.example.RestaurantManagementService.repo;

import com.example.RestaurantManagementService.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);

    User findFirstByPassword(String pass);
}
