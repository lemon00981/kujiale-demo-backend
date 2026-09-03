package com.kujiale.design.controller;

import com.kujiale.common.api.ApiResponse;
import com.kujiale.design.dto.DesignMessageRequest;
import com.kujiale.design.entity.DesignMessage;
import com.kujiale.design.repository.DesignMessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/design-messages")
public class DesignMessageController {

    private final DesignMessageRepository repository;

    public DesignMessageController(DesignMessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ApiResponse<List<DesignMessage>> list(@RequestParam String sessionId) {
        return ApiResponse.ok(repository.findBySessionIdOrderByCreatedAtAsc(sessionId));
    }

    @PostMapping
    public ApiResponse<DesignMessage> create(@RequestBody DesignMessageRequest request) {
        DesignMessage message = new DesignMessage();
        message.setSessionId(request.sessionId());
        message.setRole(request.role());
        message.setContent(request.content());
        return ApiResponse.ok(repository.save(message));
    }
}
