package com.portfolio.ancillaryservice.model;

import com.portfolio.enums.CoverageType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InsuranceCoverage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Ancillary ancillary;

    @Column(nullable = false)
    private CoverageType coverageType;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double coverageAmount;

    private boolean isFlat = true;
    private String claimCondition;
    private String emergencyContact;
    private Integer displayOrder;
    private boolean active = true;
}
