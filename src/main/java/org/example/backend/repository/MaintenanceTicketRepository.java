package org.example.backend.repository;

import org.example.backend.entity.MaintenanceTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket, Integer> {
    List<MaintenanceTicket> findByInspection_IdAndIsDeleted(int id, String isDeleted);
}
