package com.yogiting.api.point.service;

import com.yogiting.api.point.mapper.PointMapper;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    private final PointMapper pointMapper;

    public PointService(PointMapper pointMapper) {
        this.pointMapper = pointMapper;
    }

    public void createWallet(Long id) {
        pointMapper.createWallet(id);
    }

    public Long getPoint(Long memberId) {
        Long point = pointMapper.getPoint(memberId);
        return point;
    }

    public void addPoint(Long memberId, Long point) {
        pointMapper.addPoint(memberId, point);
    }

    public void removePoint(Long memberId, Long point) {
        pointMapper.removePoint(memberId, point);
    }
}
