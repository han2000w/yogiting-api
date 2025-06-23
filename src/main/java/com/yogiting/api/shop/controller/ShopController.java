package com.yogiting.api.shop.controller;

import com.yogiting.api.shop.dto.ShopItemResDto;
import com.yogiting.api.shop.dto.ShopPurchaseHistoryResDto;
import com.yogiting.api.shop.dto.ShopPurchaseReqDto;
import com.yogiting.api.shop.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<?> getItems() {
        List<ShopItemResDto> items = shopService.getItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody ShopPurchaseReqDto shopPurchaseReqDto) {
        shopService.purchase(shopPurchaseReqDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/purchase/history")
    public ResponseEntity<?> getPurchaseHistory(@RequestParam Long memberId) {

        List<ShopPurchaseHistoryResDto> histories = shopService.getPurchaseHistory(memberId);
        return ResponseEntity.ok(histories);
}

}
