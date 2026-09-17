package org.example.backend.depreciation.Record;

import jakarta.persistence.*;
import org.example.backend.asset.Asset.Asset;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "depreciation_records")
public class DepreciationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "method", nullable = false, length = 30)
    private String method;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "useful_life_months", nullable = false)
    private Integer usefulLifeMonths;

    @Column(name = "depreciation_rate", precision = 8, scale = 4)
    private BigDecimal depreciationRate;

    @Column(name = "monthly_depreciation", precision = 15, scale = 2)
    private BigDecimal monthlyDepreciation;

    @Column(name = "accumulated_depreciation", precision = 15, scale = 2)
    private BigDecimal accumulatedDepreciation = BigDecimal.ZERO;

    @Column(name = "book_value", precision = 15, scale = 2)
    private BigDecimal bookValue;

    @Column(name = "status", length = 20)
    private String status = "active";
}