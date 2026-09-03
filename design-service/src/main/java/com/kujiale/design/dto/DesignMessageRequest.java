package com.kujiale.design.dto;

/**
 * 保存一条 AI 对话历史的请求体。
 */
public record DesignMessageRequest(
        String sessionId,
        String role,
        String content
) {
}
