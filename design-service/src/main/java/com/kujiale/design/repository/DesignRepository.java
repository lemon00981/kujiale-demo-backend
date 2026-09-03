package com.kujiale.design.repository;

import com.kujiale.design.entity.Design;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignRepository extends JpaRepository<Design, Long> {

    List<Design> findAllByOrderByCreatedAtDesc();
}
