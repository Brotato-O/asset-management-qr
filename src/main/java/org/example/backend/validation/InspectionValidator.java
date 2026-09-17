package org.example.backend.validation;

import org.example.backend.entity.Inspection;
import org.example.backend.entity.MaintenanceTicket;
import org.example.backend.enums.UserStatus;
import org.example.backend.repository.AssetRepository;
import org.example.backend.repository.InspectionRepository;
import org.example.backend.repository.MaintenanceTicketRepository;
import org.example.backend.repository.UserRepository;
import org.example.backend.response.DeleteInspectionResult;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class InspectionValidator {
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final MaintenanceTicketRepository maintenanceTicketRepository;
    private final InspectionRepository inspectionRepository;

    public InspectionValidator(UserRepository userRepository, AssetRepository assetRepository, MaintenanceTicketRepository maintenanceTicketRepository, InspectionRepository inspectionRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.maintenanceTicketRepository = maintenanceTicketRepository;
        this.inspectionRepository = inspectionRepository;
    }

    public void validateInspection(Inspection inspection) {
        if (userRepository.existsByIdAndStatus(inspection.getInspector().getId(), UserStatus.ACTIVE))
            throw new RuntimeException("User not found");
        if (assetRepository.existsByIdAndIsDeleted(inspection.getAsset().getId(), "no"))
            throw new RuntimeException("Asset not found");
    }

    public DeleteInspectionResult checkDeleteInspection(int id) {
        Inspection inspection;
        try {
            inspection = inspectionRepository.findById(id).orElseThrow();
        } catch (RuntimeException e) {
            return new DeleteInspectionResult(id, false, List.of(), null);
        }

            List<MaintenanceTicket> maintenanceTickets =
                    maintenanceTicketRepository
                            .findByInspection_IdAndIsDeleted(inspection.getId(), "no");

            if (!maintenanceTickets.isEmpty()) {
                return new DeleteInspectionResult(id, false, maintenanceTickets, inspection);
            }

            return new DeleteInspectionResult(id, true, List.of(), inspection);

    }
}
