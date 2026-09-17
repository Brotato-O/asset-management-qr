package org.example.backend.asset.Category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class AssetCategoryController {
    private final AssetCategoryService assetCategoryService;

    public AssetCategoryController(AssetCategoryService assetCategoryService){
        this.assetCategoryService= assetCategoryService;
    }

    @GetMapping
    public List<AssetCategory> getCategories(){
        return assetCategoryService.getCategories();
    }

    @GetMapping("/{id}")
    public AssetCategory getCategory(@PathVariable int id){
        return assetCategoryService.getCategory(id);
    }

    @PostMapping
    public AssetCategory createCategory(@RequestBody AssetCategory category){
        return assetCategoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public AssetCategory updateCategory(@PathVariable int id, @RequestBody AssetCategory category){
        return assetCategoryService.updateCategory(id, category);
    }

    @DeleteMapping
    public List<DeleteCategoryResult> deleteCategories(@RequestBody List<Integer> ids){
        return assetCategoryService.deleteCategories(ids);
    }

    @DeleteMapping("/{id}")
    public DeleteCategoryResult deleteCategory(@PathVariable int id){
        return assetCategoryService.deleteCategory(id);
    }
}
