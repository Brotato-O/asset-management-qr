package org.example.backend.asset.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Integer> {
    List<AssetCategory> findByParentCategory_Id(int id);

    void deleteByIdIn(List<Integer> ids);
}

