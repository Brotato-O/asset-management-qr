package org.example.backend.validation;

import org.example.backend.controller.AssetController;
import org.example.backend.entity.Asset;
import org.example.backend.entity.AssetAssignment;
import org.example.backend.repository.AssetAssignmentRepository;
import org.example.backend.repository.AssetCategoryRepository;
import org.example.backend.repository.AssetRepository;
import org.example.backend.response.DeleteAssetResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetValidator {
    private final AssetCategoryRepository assetCategoryRepository;
    private final AssetAssignmentRepository assetAssignmentRepository;
    private final AssetRepository assetRepository;

    public AssetValidator(AssetCategoryRepository assetCategoryRepository, AssetAssignmentRepository assetAssignmentRepository, AssetRepository assetRepository) {
        this.assetCategoryRepository = assetCategoryRepository;
        this.assetAssignmentRepository = assetAssignmentRepository;
        this.assetRepository = assetRepository;
    }

    public void validateNewAsset(Asset asset) {
        if (!assetCategoryRepository.existsById(asset.getCategory().getId()))
            throw new RuntimeException("Category not found");
    }

    public DeleteAssetResult checkDeleteAsset(int id) {
        Asset asset;
        try {
            asset = assetRepository.findById(id).orElseThrow();
        } catch (RuntimeException e) { //?
            return new DeleteAssetResult(id, false, null, List.of(), null);
        }

        List<AssetAssignment> assetAssignments = assetAssignmentRepository.findByAsset_IdAndReturnedAtIsNull(asset.getId());
        if (!assetAssignments.isEmpty())
            return new DeleteAssetResult(asset.getId(), false, DeleteAssetResult.DeleteAssetError.HAS_ACTIVE_ASSIGNMENTS, assetAssignments, asset);
        return new DeleteAssetResult(asset.getId(), true, null, List.of(), asset);
    }
}
