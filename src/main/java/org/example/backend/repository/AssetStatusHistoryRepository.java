package org.example.backend.repository;

import org.example.backend.entity.AssetStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetStatusHistoryRepository
        extends JpaRepository<AssetStatusHistory, Integer> {
}