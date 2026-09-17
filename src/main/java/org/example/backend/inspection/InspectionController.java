package org.example.backend.inspection;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspections")
public class InspectionController {

    private final InspectionService inspectionService;

    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    // Lấy tất cả Inspection chưa bị xóa
    @GetMapping
    public List<Inspection> getActiveInspections() {
        return inspectionService.getActiveInspections();
    }

    // Lấy Inspection theo ID
    @GetMapping("/{id}")
    public Inspection getActiveInspection(@PathVariable int id) {
        return inspectionService.getActiveInspection(id);
    }

    // Tạo Inspection
    @PostMapping
    public Inspection createInspection(
            @RequestBody Inspection newInspection) {

        return inspectionService.createInspection(newInspection);
    }

    // Cập nhật Inspection
    @PutMapping("/{id}")
    public Inspection updateInspection(
            @PathVariable int id,
            @RequestBody Inspection newInspection) {

        return inspectionService.updateInspection(id, newInspection);
    }

    // Soft delete một Inspection
    @DeleteMapping("/{id}")
    public DeleteInspectionResult deleteInspection(
            @PathVariable int id) {

        return inspectionService.deleteInspection(id);
    }

    // Soft delete nhiều Inspection
    @DeleteMapping
    public List<DeleteInspectionResult> softDeleteInspections(
            @RequestBody List<Integer> ids) {

        return inspectionService.softDeleteInspections(ids);
    }
}