package org.example.backend.depreciation.Detail;

import jakarta.persistence.*;
import org.example.backend.depreciation.Record.DepreciationRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "depreciation_details")
public class DepreciationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depreciation_record_id", nullable = false)
    private DepreciationRecord depreciationRecord;

    @Column(name = "depreciation_period", nullable = false)
    private LocalDate depreciationPeriod;

    @Column(name = "opening_book_value", precision = 15, scale = 2)
    private BigDecimal openingBookValue;

    @Column(name = "depreciation_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal depreciationAmount;

    @Column(name = "accumulated_depreciation", precision = 15, scale = 2)
    private BigDecimal accumulatedDepreciation;

    @Column(name = "closing_book_value", precision = 15, scale = 2)
    private BigDecimal closingBookValue;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}