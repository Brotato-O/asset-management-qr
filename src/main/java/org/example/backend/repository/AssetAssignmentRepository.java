package org.example.backend.repository;

import org.example.backend.entity.AssetAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment, Integer> {
    List<AssetAssignment> findByAsset_IdAndReturnedAtIsNull(int id);
}
