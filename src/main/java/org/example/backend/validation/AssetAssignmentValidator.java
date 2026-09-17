package org.example.backend.validation;

import org.example.backend.entity.AssetAssignment;
import org.example.backend.enums.UserStatus;
import org.example.backend.repository.AssetRepository;
import org.example.backend.repository.UserRepository;
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

        if (!userRepository.existsByIdAndStatus(assetAssignment.getUser().getId(), UserStatus.ACTIVE)) {
            throw new RuntimeException("User not found");
        }

        if (!userRepository.existsByIdAndStatus(assetAssignment.getAssignedBy().getId(), UserStatus.ACTIVE)) {
            throw new RuntimeException("AssignedBy user not found");
        }
    }
}
