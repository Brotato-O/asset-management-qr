package org.example.backend.asset.StatusHistory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetStatusHistoryService {

    private final AssetStatusHistoryRepository assetStatusHistoryRepository;

    public AssetStatusHistoryService(
            AssetStatusHistoryRepository assetStatusHistoryRepository) {
        this.assetStatusHistoryRepository = assetStatusHistoryRepository;
    }

    public List<AssetStatusHistory> getAllStatus() {
        return assetStatusHistoryRepository.findAll();
    }
}
