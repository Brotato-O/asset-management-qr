package org.example.backend.asset.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
    Optional<Asset> findByIdAndIsDeleted(int id, String isDeleted);
    List<Asset> findByIsDeleted(String isDeleted);
    List<Asset> findByCategory_Id(int id);
    boolean existsByIdAndIsDeleted(int id, String isDeleted);

    @Modifying
    @Query("""
        update Asset a
        set a.status= LIQUIDATED
        where a.id in :ids
""")
    int liquidateAssets(@Param("ids") List<Integer> ids);
}
