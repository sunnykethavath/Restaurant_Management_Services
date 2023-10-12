package com.example.RestaurantManagementService.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String imageUrl;
    private AuthInpDto authInpDto;
}
