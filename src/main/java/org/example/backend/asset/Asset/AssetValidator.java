package org.example.backend.asset.Asset;

import org.example.backend.asset.Assignment.AssetAssignment;
import org.example.backend.asset.Assignment.AssetAssignmentRepository;
import org.example.backend.asset.Category.AssetCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetValidator {
    private final AssetCategoryRepository assetCategoryRepository;

    public AssetValidator(AssetCategoryRepository assetCategoryRepository) {
        this.assetCategoryRepository = assetCategoryRepository;
    }

    public void validateNewAsset(Asset asset) {
        if (!assetCategoryRepository.existsById(asset.getCategory().getId()))
            throw new RuntimeException("Category not found");
    }
}
