package com.yogiting.api.point.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {

    void createWallet(Long memberId);
    Long getPoint(Long memberId);
    void addPoint(Long memberId, Long point);
    void removePoint(Long memberId, Long point);
}
