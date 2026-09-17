package org.example.backend.asset.Asset;

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
    @PutMapping("/liquidate")
    public List<LiquidateAssetResult> deleteAssets(@RequestBody List<Integer> ids){
        return assetService.LiquidateAssets(ids);
    }
    @PutMapping("/{id}/liquidate")
    public LiquidateAssetResult LiquidatedAsset(@PathVariable int id){
        return assetService.LiquidateAsset(id);
    }
}
