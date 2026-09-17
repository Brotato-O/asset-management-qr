package org.example.backend.maintenance.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket, Integer> {
    List<MaintenanceTicket> findByInspection_IdAndIsDeleted(int id, String isDeleted);
}
