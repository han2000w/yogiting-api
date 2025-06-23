package com.yogiting.api.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopPurchaseHistoryResDto {

    private Long purchaseHistoryId;
    private int totalAmount;
    private List<ItemDto> items;
    private LocalDateTime createdAt;

}
