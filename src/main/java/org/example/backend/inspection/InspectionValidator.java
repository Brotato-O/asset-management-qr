package org.example.backend.inspection;

import org.example.backend.maintenance.Ticket.MaintenanceTicket;
import org.example.backend.enums.UserStatus;
import org.example.backend.asset.Asset.AssetRepository;
import org.example.backend.maintenance.Ticket.MaintenanceTicketRepository;
import org.example.backend.organization.User.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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
        if (userRepository.existsByIdAndStatus(inspection.getInspector().getId(), UserStatus.active))
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
