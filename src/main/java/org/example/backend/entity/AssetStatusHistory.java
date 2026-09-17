package org.example.backend.entity;

import jakarta.persistence.*;
import org.example.backend.enums.AssetStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "asset_status_history")
public class AssetStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_status", length = 50)
    private AssetStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", length = 50)
    private AssetStatus newStatus;

    @Column(name = "changed_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime changedAt;
}