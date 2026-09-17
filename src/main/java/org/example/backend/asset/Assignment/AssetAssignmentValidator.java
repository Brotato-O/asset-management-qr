package org.example.backend.asset.Assignment;

import org.example.backend.enums.UserStatus;
import org.example.backend.asset.Asset.AssetRepository;
import org.example.backend.organization.User.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AssetAssignmentValidator {
    AssetRepository assetRepository;
    UserRepository userRepository;
    public AssetAssignmentValidator (AssetRepository assetRepository, UserRepository userRepository){
        this.assetRepository= assetRepository;
        this.userRepository= userRepository;
    }

    public void validateAssignment(AssetAssignment assetAssignment){
        if (!assetRepository.existsByIdAndIsDeleted(assetAssignment.getAsset().getId(), "no")) {
            throw new RuntimeException("Asset not found");
        }

        if (!userRepository.existsByIdAndStatus(assetAssignment.getUser().getId(), UserStatus.active)) {
            throw new RuntimeException("User not found");
        }

        if (!userRepository.existsByIdAndStatus(assetAssignment.getAssignedBy().getId(), UserStatus.active)) {
            throw new RuntimeException("AssignedBy user not found");
        }
    }
}
