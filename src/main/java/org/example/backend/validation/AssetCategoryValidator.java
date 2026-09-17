package org.example.backend.validation;

import org.example.backend.entity.Asset;
import org.example.backend.entity.AssetCategory;
import org.example.backend.repository.AssetCategoryRepository;
import org.example.backend.repository.AssetRepository;
import org.example.backend.response.DeleteCategoryResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetCategoryValidator {
    private final AssetRepository assetRepository;
    private final AssetCategoryRepository assetCategoryRepository;

    public AssetCategoryValidator(AssetRepository assetRepository, AssetCategoryRepository assetCategoryRepository) {
        this.assetRepository = assetRepository;
        this.assetCategoryRepository = assetCategoryRepository;
    }

    public DeleteCategoryResult checkDeleteCategory(int id) {
        AssetCategory assetCategory;
        try {
            assetCategory = assetCategoryRepository.findById(id).orElseThrow();
        } catch (RuntimeException e) {
            return new DeleteCategoryResult(id, false, null, List.of(), List.of(), null);
        }
        List<AssetCategory> categories = assetCategoryRepository.findByParentCategory_Id(assetCategory.getId());
        List<Asset> assets = assetRepository.findByCategory_Id(assetCategory.getId());
        if (!categories.isEmpty()) {
            return new DeleteCategoryResult(assetCategory.getId(), false, DeleteCategoryResult.DeleteCategoryError.HAS_CHILDREN, categories, List.of(), assetCategory);
        }
        if (!assets.isEmpty()) {
            return new DeleteCategoryResult(assetCategory.getId(), false, DeleteCategoryResult.DeleteCategoryError.HAS_ASSETS, List.of(), assets, assetCategory);
        }
        return new DeleteCategoryResult(assetCategory.getId(), true, null, List.of(), List.of(), assetCategory);
    }
}
