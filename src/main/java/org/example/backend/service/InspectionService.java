package org.example.backend.service;

import org.example.backend.entity.Inspection;
import org.example.backend.entity.MaintenanceTicket;
import org.example.backend.repository.InspectionRepository;
import org.example.backend.response.DeleteInspectionResult;
import org.example.backend.validation.InspectionValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InspectionService {

    private final InspectionRepository inspectionRepository;
    private final InspectionValidator inspectionValidator;

    public InspectionService(
            InspectionRepository inspectionRepository, InspectionValidator inspectionValidator) {
        this.inspectionRepository = inspectionRepository;
        this.inspectionValidator = inspectionValidator;
    }

    // Lấy tất cả Inspection
    public List<Inspection> getActiveInspections() {
        return inspectionRepository.findByIsDeleted("no");
    }

    // Lấy Inspection theo ID
    public Inspection getActiveInspection(int id) {
        return inspectionRepository.findByIdAndIsDeleted(id, "no")
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inspection not found with id: " + id
                        )
                );
    }

    // Tạo Inspection
    public Inspection createInspection(
            Inspection newInspection) {
        inspectionValidator.validateInspection(newInspection);
        return inspectionRepository.save(newInspection);
    }

    // Cập nhật Inspection
    public Inspection updateInspection(
            int id,
            Inspection newInspection) {

        inspectionValidator.validateInspection(newInspection);
        Inspection inspection = getActiveInspection(id);

        inspection.setConditionStatus(
                newInspection.getConditionStatus()
        );
        inspection.setNote(newInspection.getNote());

        return inspectionRepository.save(inspection);
    }

    public DeleteInspectionResult deleteInspection(int id) {

        DeleteInspectionResult result =
                inspectionValidator.checkDeleteInspection(id);

        if (!result.isSuccess()) {
            return result;
        }

        Inspection inspection = result.getInspection();

        inspection.setIsDeleted("yes");
        inspection.setDeletedAt(LocalDateTime.now());

        inspectionRepository.save(inspection);

        return result;
    }

    public List<DeleteInspectionResult> softDeleteInspections(List<Integer> ids) {

        List<DeleteInspectionResult> result = new ArrayList<>();

        for (Integer id : ids) {
            result.add(inspectionValidator.checkDeleteInspection(id));
        }

        List<Integer> successIds = result.stream()
                .filter(DeleteInspectionResult::isSuccess)
                .map(DeleteInspectionResult::getId)
                .toList();

        if (!successIds.isEmpty()) {
            inspectionRepository.softDeleteInspections(successIds);
        }

        return result;
    }
}
