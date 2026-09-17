package org.example.backend.controller;

import org.example.backend.response.DeleteAssetResult;
import org.example.backend.entity.Asset;
import org.example.backend.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService){
        this.assetService= assetService;
    }

    @GetMapping
    public List<Asset> getAssets(){
        return assetService.getActiveAssets();
    }

    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable int id){
        return assetService.getActiveAsset(id);
    }

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset){
        return assetService.createAsset(asset);
    }

    @PutMapping("/{id}")
    public Asset updateAsset(@PathVariable int id, @RequestBody Asset asset){
        return assetService.updateAsset(id, asset);
    }
    @DeleteMapping
    public List<DeleteAssetResult> deleteAssets(@RequestBody List<Integer> ids){
        return assetService.softDeleteAssets(ids);
    }
    @DeleteMapping("/{id}")
    public DeleteAssetResult deleteAsset(@PathVariable int id){
        return assetService.softDeleteAsset(id);
    }
}
