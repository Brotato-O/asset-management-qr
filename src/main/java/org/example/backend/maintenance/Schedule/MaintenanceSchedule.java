package org.example.backend.maintenance.Schedule;


import jakarta.persistence.*;
import org.example.backend.asset.Asset.Asset;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance_schedules")
public class MaintenanceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "maintenance_type", length = 30)
    private String maintenanceType;

    @Column(name = "cycle", length = 50)
    private String cycle;

    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;

    @Column(name = "next_maintenance_date")
    private LocalDate nextMaintenanceDate;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "status", length = 20)
    private String status = "active";
}