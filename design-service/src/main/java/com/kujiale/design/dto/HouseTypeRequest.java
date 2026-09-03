package com.kujiale.design.dto;

/**
 * 创建户型的请求体。
 */
public record HouseTypeRequest(
        String name,
        Double area,
        String layoutJson
) {
}
