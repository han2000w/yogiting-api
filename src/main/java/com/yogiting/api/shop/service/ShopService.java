package com.yogiting.api.shop.service;

import com.yogiting.api.point.service.PointService;
import com.yogiting.api.shop.dto.ShopItemResDto;
import com.yogiting.api.shop.dto.ShopPurchaseHistoryResDto;
import com.yogiting.api.shop.dto.ShopPurchaseReqDto;
import com.yogiting.api.shop.mapper.ShopMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShopService {

    private final ShopMapper shopMapper;
    private final PointService pointService;

    public ShopService(ShopMapper shopMapper, PointService pointService) {
        this.shopMapper = shopMapper;
        this.pointService = pointService;
    }

    public List<ShopItemResDto> getItems() {
        List<ShopItemResDto> items = shopMapper.getItems();
        return items;
    }

    public void purchase(ShopPurchaseReqDto shopPurchaseReqDto) {
        shopMapper.addPurchaseHistory(shopPurchaseReqDto);
        shopMapper.addPurchaseItem(shopPurchaseReqDto.getPurchaseId(), shopPurchaseReqDto.getItems());
        pointService.removePoint(shopPurchaseReqDto.getMemberId(), shopPurchaseReqDto.getTotalAmount());
    }

    public List<ShopPurchaseHistoryResDto> getPurchaseHistory(Long memberId) {
        List<ShopPurchaseHistoryResDto> histories = shopMapper.getPurchaseHistory(memberId);
        return histories;
    }
}
