package org.example.backend.service;

import org.example.backend.entity.MaintenanceTicket;
import org.example.backend.repository.MaintenanceTicketRepository;
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
