package com.yogiting.api.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long itemId;
    private String itemName;
    private int price;
    private int quantity;
}
