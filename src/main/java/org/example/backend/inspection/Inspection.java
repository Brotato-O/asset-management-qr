package org.example.backend.inspection;

import jakarta.persistence.*;
import org.example.backend.asset.Asset.Asset;
import org.example.backend.enums.AssetCondition;
import org.example.backend.organization.User.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inspections")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspector_id", nullable = false)
    private User inspector;

    @Column(name = "inspected_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime inspectedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_status", nullable = false, length = 30)
    private AssetCondition conditionStatus;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "is_deleted", nullable = false, length = 3)
    private String isDeleted = "no";

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Constructors
    public Inspection() {
    }

    public Inspection(Integer id, Asset asset, User inspector, LocalDateTime inspectedAt,
                      AssetCondition conditionStatus, BigDecimal latitude, BigDecimal longitude,
                      String note, LocalDateTime createdAt, String isDeleted, LocalDateTime deletedAt) {
        this.id = id;
        this.asset = asset;
        this.inspector = inspector;
        this.inspectedAt = inspectedAt;
        this.conditionStatus = conditionStatus;
        this.latitude = latitude;
        this.longitude = longitude;
        this.note = note;
        this.createdAt = createdAt;
        this.isDeleted= isDeleted;
        this.deletedAt= deletedAt;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    // Getters and Setters
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

    public User getInspector() {
        return inspector;
    }

    public void setInspector(User inspector) {
        this.inspector = inspector;
    }

    public LocalDateTime getInspectedAt() {
        return inspectedAt;
    }

    public void setInspectedAt(LocalDateTime inspectedAt) {
        this.inspectedAt = inspectedAt;
    }

    public AssetCondition getConditionStatus() {
        return conditionStatus;
    }

    public void setConditionStatus(AssetCondition conditionStatus) {
        this.conditionStatus = conditionStatus;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}