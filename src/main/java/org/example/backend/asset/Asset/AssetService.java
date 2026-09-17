package org.example.backend.asset.Asset;

import org.example.backend.asset.Assignment.AssetAssignment;
import org.example.backend.asset.Assignment.AssetAssignmentRepository;
import org.example.backend.asset.StatusHistory.AssetStatusHistory;
import org.example.backend.asset.StatusHistory.AssetStatusHistoryRepository;
import org.example.backend.enums.AssetStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {
    private final AssetAssignmentRepository assetAssignmentRepository;
    private final AssetRepository assetRepository;
    private final AssetValidator assetValidator;
    private final AssetStatusHistoryRepository assetStatusHistoryRepository;

    public AssetService(AssetAssignmentRepository assetAssignmentRepository, AssetRepository assetRepository, AssetValidator assetValidator, AssetStatusHistoryRepository assetStatusHistoryRepository){
        this.assetAssignmentRepository = assetAssignmentRepository;
        this.assetRepository= assetRepository;
        this.assetValidator = assetValidator;
        this.assetStatusHistoryRepository = assetStatusHistoryRepository;
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

    @Transactional
    public LiquidateAssetResult LiquidateAsset(int id){
        LiquidateAssetResult liquidateAssetResult = checkDeleteAsset(id);
        if (!liquidateAssetResult.isSuccess()) return liquidateAssetResult;
        Asset asset= liquidateAssetResult.getAsset();
        asset.setStatus(AssetStatus.LIQUIDATED);

        AssetStatusHistory history = new AssetStatusHistory();

        history.setAsset(asset);
        history.setOldStatus(asset.getStatus());
        history.setNewStatus(AssetStatus.LIQUIDATED);

        assetStatusHistoryRepository.save(history);

        assetRepository.save(asset);
        return liquidateAssetResult;
    }

    @Transactional
    public List<LiquidateAssetResult> LiquidateAssets(List<Integer> ids) {

        List<LiquidateAssetResult> liquidateAssetResults = new ArrayList<>();

        for (Integer id : ids) {
            liquidateAssetResults.add(checkDeleteAsset(id));
        }
        List<Integer> successIds= liquidateAssetResults.stream().filter(LiquidateAssetResult::isSuccess).map(LiquidateAssetResult::getId).toList();
        if (!successIds.isEmpty()) {
            List<Asset> assets = assetRepository.findAllById(successIds);

            for (Asset asset : assets) {
                AssetStatusHistory history = new AssetStatusHistory();

                history.setAsset(asset);
                history.setOldStatus(asset.getStatus());
                history.setNewStatus(AssetStatus.LIQUIDATED);

                assetStatusHistoryRepository.save(history);
            }
            assetRepository.liquidateAssets(successIds);
        }
        return liquidateAssetResults;
    }

    public LiquidateAssetResult checkDeleteAsset(int id) {
        Asset asset;
        try {
            asset = assetRepository.findById(id).orElseThrow(() -> new RuntimeException("loi"));
        } catch (RuntimeException e) { //?
            return new LiquidateAssetResult(id, false, null, List.of(), null);
        }

        List<AssetAssignment> assetAssignments = assetAssignmentRepository.findByAsset_IdAndReturnedAtIsNull(asset.getId());
        if (!assetAssignments.isEmpty())
            return new LiquidateAssetResult(asset.getId(), false, LiquidateAssetResult.DeleteAssetError.HAS_ACTIVE_ASSIGNMENTS, assetAssignments, asset);
        return new LiquidateAssetResult(asset.getId(), true, null, List.of(), asset);
    }
}
