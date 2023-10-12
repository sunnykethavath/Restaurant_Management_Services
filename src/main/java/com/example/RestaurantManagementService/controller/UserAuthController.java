package com.example.RestaurantManagementService.controller;

import com.example.RestaurantManagementService.services.UserAuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthController {
    @Autowired
    UserAuthTokenService userAuthTokenService;
}
