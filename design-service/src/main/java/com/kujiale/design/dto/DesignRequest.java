package com.kujiale.design.dto;

/**
 * 创建设计方案的请求体（前端保存 AI 生成结果时调用）。
 */
public record DesignRequest(
        Long userId,
        Long houseTypeId,
        String title,
        String style,
        String prompt,
        String planJson,
        String thumbnail
) {
}
