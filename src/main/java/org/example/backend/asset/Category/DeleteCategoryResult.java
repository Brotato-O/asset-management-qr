package org.example.backend.asset.Category;

import org.example.backend.asset.Asset.Asset;

import java.util.List;

public class DeleteCategoryResult {
    public enum DeleteCategoryError {
        HAS_CHILDREN,
        HAS_ASSETS
    }

    private final int id;

    private final boolean success;

    private final DeleteCategoryError error;

    private final List<AssetCategory> categories;

    private final List<Asset> assets;

    private final AssetCategory assetCategory;

    public DeleteCategoryResult(
            int id, boolean success,
            DeleteCategoryError error,
            List<AssetCategory> categories,
            List<Asset> assets, AssetCategory assetCategory) {
        this.id = id;

        this.success = success;
        this.error = error;
        this.categories = categories;
        this.assets = assets;
        this.assetCategory = assetCategory;
    }

    public boolean isSuccess() {
        return success;
    }

    public AssetCategory getAssetCategory(){
        return assetCategory;
    }

    public DeleteCategoryError getError() {
        return error;
    }

    public int getId() {
        return id;
    }

    public List<AssetCategory> getCategories() {
        return categories;
    }

    public List<Asset> getAssets() {
        return assets;
    }
}