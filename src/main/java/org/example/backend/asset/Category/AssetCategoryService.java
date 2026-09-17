package org.example.backend.asset.Category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetCategoryService {
    private final AssetCategoryRepository assetCategoryRepository;
    private final AssetCategoryValidator assetCategoryValidator;

    public AssetCategoryService(AssetCategoryRepository assetCategoryRepository, AssetCategoryValidator assetCategoryValidator) {
        this.assetCategoryRepository = assetCategoryRepository;
        this.assetCategoryValidator = assetCategoryValidator;
    }

    public List<AssetCategory> getCategories() {
        return assetCategoryRepository.findAll();
    }

    public AssetCategory getCategory(int id) {
        return assetCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public AssetCategory createCategory(AssetCategory newAssetCategory) {
        AssetCategory parentCategory= null;

        if (newAssetCategory.getParentCategory()!= null)
            if(!existsCategory(newAssetCategory.getParentCategory().getId()))
                throw new RuntimeException("Category not found");
            else parentCategory= newAssetCategory.getParentCategory();

        newAssetCategory.setParentCategory(parentCategory);
        return assetCategoryRepository.save(newAssetCategory);
    }

    public AssetCategory updateCategory(int id, AssetCategory newAssetCategory){
        AssetCategory category = getCategory(id);

        AssetCategory parentCategory =null;
        if (newAssetCategory.getParentCategory()!= null)
            if(!existsCategory(newAssetCategory.getParentCategory().getId()))
                throw new RuntimeException("Category not found");
            else parentCategory= newAssetCategory.getParentCategory();

        category.setParentCategory(parentCategory);
        category.setName(newAssetCategory.getName());
        return assetCategoryRepository.save(category);
    }

    public List<DeleteCategoryResult> deleteCategories(List<Integer> ids){
        List<DeleteCategoryResult> results= new ArrayList<>();
        for (Integer id: ids) {
            DeleteCategoryResult deleteCategoryResult= assetCategoryValidator.checkDeleteCategory(id);
            results.add(deleteCategoryResult);
        }
        List<Integer> successIds= results.stream().filter(DeleteCategoryResult::isSuccess).map(DeleteCategoryResult::getId).toList();
        if (!successIds.isEmpty())
            assetCategoryRepository.deleteByIdIn(successIds);
        return results;
    }

    public DeleteCategoryResult deleteCategory(int id){

        DeleteCategoryResult deleteCategoryResult= assetCategoryValidator.checkDeleteCategory(id);
        if (!deleteCategoryResult.isSuccess()) return deleteCategoryResult;

        assetCategoryRepository.deleteById(id);
        return deleteCategoryResult;
    }

    public boolean existsCategory(int id){
        return assetCategoryRepository.existsById(id);
    }

    public List<AssetCategory> getCategoriesByParentId(int id){
        return assetCategoryRepository.findByParentCategory_Id(id);
    }
}
