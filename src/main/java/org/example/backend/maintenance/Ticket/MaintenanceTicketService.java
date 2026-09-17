package org.example.backend.maintenance.Ticket;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceTicketService {
    private final MaintenanceTicketRepository maintenanceTicketRepository;

    public MaintenanceTicketService(MaintenanceTicketRepository maintenanceTicketRepository) {
        this.maintenanceTicketRepository = maintenanceTicketRepository;
    }

    public List<MaintenanceTicket> getActiveMaintenanceByInspection(int id){
        return maintenanceTicketRepository.findByInspection_IdAndIsDeleted(id, "no");
    }
}
