package com.yogiting.api.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopItemResDto {

    private Long id;
    private String name;
    private String description;
    private int price;
    private String imageUrl;
}
