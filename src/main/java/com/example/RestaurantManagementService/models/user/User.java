package com.example.RestaurantManagementService.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @JsonIgnore
    private LocalDateTime localDateTime;

    public User(String name,String email,String password,Role role){
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
        localDateTime=LocalDateTime.now();
    }
}
