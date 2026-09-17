package org.example.backend.inspection;

import org.example.backend.maintenance.Ticket.MaintenanceTicket;

import java.util.List;

public class DeleteInspectionResult {

    private int id;
    private boolean success;
    private List<MaintenanceTicket> maintenanceTickets;
    private Inspection inspection;

    public DeleteInspectionResult(
            int id,
            boolean success,
            List<MaintenanceTicket> maintenanceTickets,
            Inspection inspection
    ) {
        this.id= id;
        this.success = success;
        this.maintenanceTickets = maintenanceTickets;
        this.inspection= inspection;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getId() {
        return id;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public List<MaintenanceTicket> getMaintenanceTickets() {
        return maintenanceTickets;
    }
}
