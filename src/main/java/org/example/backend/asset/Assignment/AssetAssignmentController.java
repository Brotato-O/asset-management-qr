package org.example.backend.asset.Assignment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssetAssignmentController {

    private final AssetAssignmentService assetAssignmentService;

    public AssetAssignmentController(
            AssetAssignmentService assetAssignmentService) {
        this.assetAssignmentService = assetAssignmentService;
    }

    // Lấy tất cả Assignment
    @GetMapping
    public List<AssetAssignment> getAssignments() {
        return assetAssignmentService.getAssignments();
    }

    // Lấy Assignment theo ID
    @GetMapping("/{id}")
    public AssetAssignment getAssignment(@PathVariable int id) {
        return assetAssignmentService.getAssignment(id);
    }

    // Giao tài sản
    @PostMapping
    public AssetAssignment createAssignment(
            @RequestBody AssetAssignment assignment) {

        return assetAssignmentService.createAssignment(assignment);
    }

    // Cập nhật thông tin Assignment
    @PutMapping("/{id}")
    public AssetAssignment updateAssignment(
            @PathVariable int id,
            @RequestBody AssetAssignment assignment) {

        return assetAssignmentService.updateAssignment(id, assignment);
    }

    // Trả tài sản
    @PutMapping("/{id}/return")
    public AssetAssignment returnAsset(
            @PathVariable int id) {

        return assetAssignmentService.returnAsset(id);
    }

    // Lấy các Assignment đang còn hiệu lực của một Asset
//    @GetMapping("/asset/{assetId}/active")
//    public List<AssetAssignment> getActiveAssignmentByAssetId(
//            @PathVariable int assetId) {
//
//        return assetAssignmentService
//                .getActiveAssignmentByAssetId(assetId);
//    }
}
