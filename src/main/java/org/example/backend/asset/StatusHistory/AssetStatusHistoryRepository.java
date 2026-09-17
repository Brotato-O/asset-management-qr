package org.example.backend.asset.StatusHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetStatusHistoryRepository
        extends JpaRepository<AssetStatusHistory, Integer> {
}