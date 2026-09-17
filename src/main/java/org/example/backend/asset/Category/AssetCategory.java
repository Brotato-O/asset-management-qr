package org.example.backend.asset.Category;

import jakarta.persistence.*;

@Entity
@Table(name = "asset_categories")
public class AssetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private AssetCategory parentCategory;

    // Constructors
    public AssetCategory() {
    }

    public AssetCategory(Integer id, String name, AssetCategory parentCategory) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(AssetCategory parentCategory) {
        this.parentCategory = parentCategory;
    }
}