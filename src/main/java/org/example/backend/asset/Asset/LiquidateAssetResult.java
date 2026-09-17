package org.example.backend.asset.Asset;

import org.example.backend.asset.Assignment.AssetAssignment;

import java.util.List;

public class LiquidateAssetResult {

    public enum DeleteAssetError {
        HAS_ACTIVE_ASSIGNMENTS,
//        HAS_ACTIVE_INSPECTIONS,
//        HAS_ACTIVE_MAINTENANCE_TICKETS,
//        HAS_DEPRECIATION_RECORDS
    }

    private int id;
    private boolean success;
    private DeleteAssetError error;
    private Asset asset;

    private List<AssetAssignment> assignments;
//    private List<Inspection> inspections;
//    private List<MaintenanceTicket> maintenanceTickets;
//    private List<DepreciationRecord> depreciationRecords;

    // constructor + getter

    public LiquidateAssetResult(
            int id,
            boolean success,
            DeleteAssetError error,
            List<AssetAssignment> assignments,
            Asset asset
//            List<Inspection> inspections,
//            List<MaintenanceTicket> maintenanceTickets,
//            List<DepreciationRecord> depreciationRecords
    ) {
        this.id= id;
        this.success = success;
        this.error = error;
        this.assignments = assignments;
        this.asset= asset;
//        this.inspections = inspections;
//        this.maintenanceTickets = maintenanceTickets;
//        this.depreciationRecords = depreciationRecords;
    }


// Getter / Setter


    public int getId() {
        return id;
    }
    public Asset getAsset(){
        return asset;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DeleteAssetError getError() {
        return error;
    }

    public void setError(DeleteAssetError error) {
        this.error = error;
    }

    public List<AssetAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssetAssignment> assignments) {
        this.assignments = assignments;
    }

//    public List<Inspection> getInspections() {
//        return inspections;
//    }
//
//    public void setInspections(List<Inspection> inspections) {
//        this.inspections = inspections;
//    }
//
//    public List<MaintenanceTicket> getMaintenanceTickets() {
//        return maintenanceTickets;
//    }
//
//    public void setMaintenanceTickets(List<MaintenanceTicket> maintenanceTickets) {
//        this.maintenanceTickets = maintenanceTickets;
//    }
//
//    public List<DepreciationRecord> getDepreciationRecords() {
//        return depreciationRecords;
//    }
//
//    public void setDepreciationRecords(
//            List<DepreciationRecord> depreciationRecords
//    ) {
//        this.depreciationRecords = depreciationRecords;
//    }

}
