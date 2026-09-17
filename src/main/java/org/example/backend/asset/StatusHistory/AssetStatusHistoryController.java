package org.example.backend.asset.StatusHistory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status-history")
public class AssetStatusHistoryController {
    private final AssetStatusHistoryService assetStatusHistoryService;

    public AssetStatusHistoryController(AssetStatusHistoryService assetStatusHistoryService) {
        this.assetStatusHistoryService = assetStatusHistoryService;
    }

    @GetMapping
    public List<AssetStatusHistory> getAllStatus() {
        return assetStatusHistoryService.getAllStatus();
    }
}
