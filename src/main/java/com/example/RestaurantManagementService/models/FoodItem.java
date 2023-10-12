package com.example.RestaurantManagementService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime creatingDateTime;
    private String imageUrl;
    @Transient
    public String getHtmlLink() {
        if (imageUrl != null) {
            return "<a href='" + imageUrl + "'>" + imageUrl + "</a>";
        }
        return "";
    }

    public FoodItem(String title,String description,String imageUrl){
        this.title=title;
        this.description=description;
        this.imageUrl=imageUrl;
        creatingDateTime=LocalDateTime.now();
    }
}
