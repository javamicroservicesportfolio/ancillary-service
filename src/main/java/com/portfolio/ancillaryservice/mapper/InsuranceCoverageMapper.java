package com.portfolio.ancillaryservice.mapper;

import com.portfolio.ancillaryservice.model.Ancillary;
import com.portfolio.ancillaryservice.model.InsuranceCoverage;
import com.portfolio.payload.request.InsuranceCoverageRequest;
import com.portfolio.payload.response.InsuranceCoverageResponse;

public class InsuranceCoverageMapper {
    public static InsuranceCoverage toEntity(InsuranceCoverageRequest coverageRequest, Ancillary ancillary) {
        if (coverageRequest == null) {
            return null;
        }

        return InsuranceCoverage.builder()
                .ancillary(ancillary)
                .coverageType(coverageRequest.getCoverageType())
                .name(coverageRequest.getName())
                .description(coverageRequest.getDescription())
                .coverageAmount(coverageRequest.getCoverageAmount())
                .isFlat(coverageRequest.getIsFlat())
                .claimCondition(coverageRequest.getClaimCondition())
                .displayOrder(coverageRequest.getDisplayOrder())
                .active(coverageRequest.getActive())
                .build();
    }

    public static InsuranceCoverageResponse toResponse(InsuranceCoverage entity) {
        if (entity == null) {
            return null;
        }

        return InsuranceCoverageResponse.builder()
                .id(entity.getId())
                .ancillaryId(entity.getAncillary().getId())
                .ancillaryName(entity.getAncillary().getName())
                .coverageType(String.valueOf(entity.getCoverageType()))
                .name(entity.getName())
                .description(entity.getDescription())
                .coverageAmount(entity.getCoverageAmount())
                .isFlat(entity.isFlat())
                .claimCondition(entity.getClaimCondition())
                .emergencyContact(entity.getEmergencyContact())
                .displayOrder(entity.getDisplayOrder())
                .active(entity.isActive())
                .build();
    }

    public static void updateEntity(InsuranceCoverage entity, InsuranceCoverageRequest coverageRequest, Ancillary ancillary) {
        if (entity == null || coverageRequest == null) {
            return;
        }

        if (ancillary != null) {
            entity.setAncillary(ancillary);
        }
        if (coverageRequest.getCoverageType() != null) {
            entity.setCoverageType(coverageRequest.getCoverageType());
        }
        if (coverageRequest.getName() != null) {
            entity.setName(coverageRequest.getName());
        }
        if (coverageRequest.getDescription() != null) {
            entity.setDescription(coverageRequest.getDescription());
        }
        if (coverageRequest.getCoverageAmount() != null) {
            entity.setCoverageAmount(coverageRequest.getCoverageAmount());
        }
        if (coverageRequest.getIsFlat() != null) {
            entity.setFlat(coverageRequest.getIsFlat());
        }
        if (coverageRequest.getClaimCondition() != null) {
            entity.setClaimCondition(coverageRequest.getClaimCondition());
        }
        if (coverageRequest.getDisplayOrder() != null) {
            entity.setDisplayOrder(coverageRequest.getDisplayOrder());
        }
        if (coverageRequest.getActive() != null) {
            entity.setActive(coverageRequest.getActive());
        }
        if (coverageRequest.getEmergencyContact() != null) {
            entity.setEmergencyContact(coverageRequest.getEmergencyContact());
        }
    }
}
