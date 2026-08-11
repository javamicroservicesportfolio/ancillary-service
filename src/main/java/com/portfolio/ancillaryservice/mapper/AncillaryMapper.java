package com.portfolio.ancillaryservice.mapper;

import com.portfolio.ancillaryservice.model.Ancillary;
import com.portfolio.payload.response.AncillaryResponse;
import com.portfolio.payload.response.InsuranceCoverageResponse;

import java.util.List;

public class AncillaryMapper {
    public static AncillaryResponse toResponse(Ancillary ancillary, List<InsuranceCoverageResponse> coverages) {
        if (ancillary == null) {
            return null;
        }

        return AncillaryResponse.builder()
                .id(ancillary.getId())
                .type(ancillary.getType())
                .subType(ancillary.getSubType())
                .rfisc(ancillary.getRfisc())
                .name(ancillary.getName())
                .description(ancillary.getDescription())
                .metadata(ancillary.getMetadata())
                .coverages(coverages)
                .displayOrder(ancillary.getDisplayOrder())
                .airlineId(ancillary.getAirlineId())
                .build();

    }
}
