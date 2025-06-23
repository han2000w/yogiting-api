package com.yogiting.api.point.controller;

import com.yogiting.api.point.service.PointService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/point")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping("/wallet")
    public void createWallet(@RequestBody Long memberId) {
        pointService.createWallet(memberId);
    }

    @GetMapping("/wallet")
    public Long getPointFromWallet(@RequestParam Long memberId) {
        return pointService.getPoint(memberId);
    }
}
