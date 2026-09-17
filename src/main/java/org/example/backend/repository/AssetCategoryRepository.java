package org.example.backend.repository;

import org.example.backend.entity.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Integer> {
    List<AssetCategory> findByParentCategory_Id(int id);

    void deleteByIdIn(List<Integer> ids);
}

