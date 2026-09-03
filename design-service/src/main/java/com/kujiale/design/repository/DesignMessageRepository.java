package com.kujiale.design.repository;

import com.kujiale.design.entity.DesignMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignMessageRepository extends JpaRepository<DesignMessage, Long> {

    List<DesignMessage> findBySessionIdOrderByCreatedAtAsc(String sessionId);
}
