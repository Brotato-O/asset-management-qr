package org.example.backend.asset.StatusHistory;

import jakarta.persistence.*;
import org.example.backend.asset.Asset.Asset;
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

    @Column(
            name = "changed_at",
            nullable = false,
            updatable = false,
            insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    private LocalDateTime changedAt;

    // Constructor rỗng - cần cho JPA
    public AssetStatusHistory() {
    }

    // Constructor dùng khi tạo history
    public AssetStatusHistory(
            Asset asset,
            AssetStatus oldStatus,
            AssetStatus newStatus
    ) {
        this.asset = asset;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    // Getter / Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public AssetStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(AssetStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public AssetStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(AssetStatus newStatus) {
        this.newStatus = newStatus;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}
