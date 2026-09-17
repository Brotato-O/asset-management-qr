package org.example.backend.service;

import org.example.backend.entity.AssetAssignment;
import org.example.backend.repository.AssetAssignmentRepository;
import org.example.backend.validation.AssetAssignmentValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetAssignmentService {

    private final AssetAssignmentRepository assetAssignmentRepository;
    private final AssetAssignmentValidator assetAssignmentValidator;

    public AssetAssignmentService(
            AssetAssignmentRepository assetAssignmentRepository, AssetAssignmentValidator assetAssignmentValidator) {
        this.assetAssignmentRepository = assetAssignmentRepository;
        this.assetAssignmentValidator = assetAssignmentValidator;
    }

    // Lấy tất cả
    public List<AssetAssignment> getAssignments() {
        return assetAssignmentRepository.findAll();
    }

    // Lấy theo ID
    public AssetAssignment getAssignment(int id) {
        return assetAssignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Asset assignment not found with id: " + id
                        )
                );
    }

    // Thêm
    public AssetAssignment createAssignment(
            AssetAssignment newAssignment) {

        assetAssignmentValidator.validateAssignment(newAssignment);

        return assetAssignmentRepository.save(newAssignment);
    }

    // Cập nhật
    public AssetAssignment updateAssignment(
            int id,
            AssetAssignment newAssignment) {

        assetAssignmentValidator.validateAssignment(newAssignment);

        AssetAssignment assignment = getAssignment(id);

        assignment.setAssignedBy(newAssignment.getAssignedBy());
        assignment.setNote(newAssignment.getNote());

        return assetAssignmentRepository.save(assignment);
    }

    // Xóa
    public AssetAssignment returnAsset(int id) {

        AssetAssignment assignment = getAssignment(id);

        if (assignment.getReturnedAt() != null) {
            throw new RuntimeException("Asset has already been returned");
        }

        assignment.setReturnedAt(LocalDateTime.now());

        return assetAssignmentRepository.save(assignment);
    }

    public List<AssetAssignment> getActiveAssignmentByAssetId(int id){
        return assetAssignmentRepository.findByAsset_IdAndReturnedAtIsNull(id);
    }
}