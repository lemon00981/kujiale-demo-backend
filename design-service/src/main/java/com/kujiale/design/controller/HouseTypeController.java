package com.kujiale.design.controller;

import com.kujiale.common.api.ApiResponse;
import com.kujiale.design.dto.HouseTypeRequest;
import com.kujiale.design.entity.HouseType;
import com.kujiale.design.repository.HouseTypeRepository;
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
@RequestMapping("/api/house-types")
public class HouseTypeController {

    private final HouseTypeRepository repository;

    public HouseTypeController(HouseTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ApiResponse<List<HouseType>> list() {
        return ApiResponse.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<HouseType> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ApiResponse::ok)
                .orElseGet(() -> ApiResponse.error("户型不存在"));
    }

    @PostMapping
    public ApiResponse<HouseType> create(@RequestBody HouseTypeRequest request) {
        HouseType houseType = new HouseType();
        houseType.setName(request.name());
        houseType.setArea(request.area());
        houseType.setLayoutJson(request.layoutJson());
        return ApiResponse.ok(repository.save(houseType));
    }

    @PutMapping("/{id}")
    public ApiResponse<HouseType> update(@PathVariable Long id, @RequestBody HouseTypeRequest request) {
        return repository.findById(id).map(existing -> {
            if (request.name() != null) existing.setName(request.name());
            if (request.area() != null) existing.setArea(request.area());
            if (request.layoutJson() != null) existing.setLayoutJson(request.layoutJson());
            return ApiResponse.ok(repository.save(existing));
        }).orElseGet(() -> ApiResponse.error("户型不存在"));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ApiResponse.ok(null);
    }
}
