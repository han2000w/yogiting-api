package com.yogiting.api.shop.mapper;

import com.yogiting.api.shop.dto.ItemDto;
import com.yogiting.api.shop.dto.ShopItemResDto;
import com.yogiting.api.shop.dto.ShopPurchaseHistoryResDto;
import com.yogiting.api.shop.dto.ShopPurchaseReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {

    List<ShopItemResDto> getItems();
    void addPurchaseHistory(ShopPurchaseReqDto shopPurchaseReqDto);
    void addPurchaseItem(@Param("purchaseId") Long purchaseId, @Param("itemDto") List<ItemDto> itemDto);
    List<ShopPurchaseHistoryResDto> getPurchaseHistory(Long memberId);
}
