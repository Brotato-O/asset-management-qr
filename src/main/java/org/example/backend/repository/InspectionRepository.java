package org.example.backend.repository;

import org.example.backend.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
    List<Inspection> findByIsDeleted(String isDeleted);
    Optional<Inspection> findByIdAndIsDeleted(int id, String isDeleted);

    @Modifying
    @Query("""
    update Inspection i
    set i.isDeleted= "yes",
        i.deletedAt= CURRENT_TIMESTAMP
    where i.id in :ids
    and i.isDeleted="no"
""")
    int softDeleteInspections(@Param("ids") List<Integer> ids);
}
