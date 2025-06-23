package com.yogiting.api.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopPurchaseReqDto {

    private Long memberId;
    private List<ItemDto> items;
    private Long totalAmount;
    private String phoneNumber;

    private Long purchaseId;


}
