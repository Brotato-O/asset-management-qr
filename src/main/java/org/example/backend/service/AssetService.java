package org.example.backend.service;

import jakarta.transaction.Transactional;
import org.example.backend.response.DeleteAssetResult;
import org.example.backend.entity.Asset;
import org.example.backend.entity.AssetAssignment;
import org.example.backend.repository.AssetRepository;
import org.example.backend.validation.AssetValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetValidator assetValidator;

    public AssetService(AssetRepository assetRepository, AssetValidator assetValidator){
        this.assetRepository= assetRepository;
        this.assetValidator = assetValidator;
    }

    public List<Asset> getActiveAssets(){
        return assetRepository.findByIsDeleted("no");
    }

    public List<Asset> getAssets(){
        return assetRepository.findAll();
    }

    public Asset getActiveAsset(int id) {
        return assetRepository.findByIdAndIsDeleted(id, "no")
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
    }

    public Asset getAsset(int id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
    }

    public Asset createAsset(Asset asset){
        assetValidator.validateNewAsset(asset);
        return assetRepository.save(asset);
    }

    public Asset updateAsset(int id, Asset newAsset){
        Asset asset= getAsset(id);

        asset.setName(newAsset.getName());
        assetValidator.validateNewAsset(asset);
        asset.setCategory(newAsset.getCategory());
        asset.setDescription(newAsset.getDescription());
        asset.setPurchasePrice(newAsset.getPurchasePrice());
        asset.setWarrantyExpiryDate(newAsset.getWarrantyExpiryDate());

        return assetRepository.save(asset);
    }

    public DeleteAssetResult softDeleteAsset(int id){
        DeleteAssetResult deleteAssetResult= assetValidator.checkDeleteAsset(id);
        if (!deleteAssetResult.isSuccess()) return deleteAssetResult;
        Asset asset= deleteAssetResult.getAsset();
        asset.setIsDeleted("yes");
        asset.setDeletedAt(LocalDateTime.now());

        assetRepository.save(asset);
        return deleteAssetResult;
    }

    public List<DeleteAssetResult> softDeleteAssets(List<Integer> ids) {

        List<DeleteAssetResult> deleteAssetResults= new ArrayList<>();

        for (Integer id : ids) {
            deleteAssetResults.add(assetValidator.checkDeleteAsset(id));
        }
        List<Integer> successIds= deleteAssetResults.stream().filter(DeleteAssetResult::isSuccess).map(DeleteAssetResult::getId).toList();
        if (!successIds.isEmpty()) assetRepository.softDeleteAssets(successIds);
        return deleteAssetResults;
    }

    public List<Asset> getAssetByCategoryId(int id){
        return assetRepository.findByCategory_Id(id);
    }

    public boolean existsActiveAsset(int id){
        return assetRepository.existsByIdAndIsDeleted(id, "no");
    }
}
