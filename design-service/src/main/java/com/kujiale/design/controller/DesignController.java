package com.kujiale.design.controller;

import com.kujiale.common.api.ApiResponse;
import com.kujiale.design.dto.DesignRequest;
import com.kujiale.design.entity.Design;
import com.kujiale.design.repository.DesignRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/designs")
public class DesignController {

    private final DesignRepository repository;

    public DesignController(DesignRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ApiResponse<List<Design>> list() {
        return ApiResponse.ok(repository.findAllByOrderByCreatedAtDesc());
    }

    @GetMapping("/{id}")
    public ApiResponse<Design> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ApiResponse::ok)
                .orElseGet(() -> ApiResponse.error("方案不存在"));
    }

    @PostMapping
    public ApiResponse<Design> create(@RequestBody DesignRequest request) {
        Design design = new Design();
        design.setUserId(request.userId());
        design.setHouseTypeId(request.houseTypeId());
        design.setTitle(request.title());
        design.setStyle(request.style());
        design.setPrompt(request.prompt());
        design.setPlanJson(request.planJson());
        design.setThumbnail(request.thumbnail());
        design.setStatus("saved");
        return ApiResponse.ok(repository.save(design));
    }

    @PutMapping("/{id}")
    public ApiResponse<Design> update(@PathVariable Long id, @RequestBody DesignRequest request) {
        return repository.findById(id).map(existing -> {
            if (request.userId() != null) existing.setUserId(request.userId());
            if (request.houseTypeId() != null) existing.setHouseTypeId(request.houseTypeId());
            if (request.title() != null) existing.setTitle(request.title());
            if (request.style() != null) existing.setStyle(request.style());
            if (request.prompt() != null) existing.setPrompt(request.prompt());
            if (request.planJson() != null) existing.setPlanJson(request.planJson());
            if (request.thumbnail() != null) existing.setThumbnail(request.thumbnail());
            return ApiResponse.ok(repository.save(existing));
        }).orElseGet(() -> ApiResponse.error("方案不存在"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ApiResponse.ok(null);
    }
}
