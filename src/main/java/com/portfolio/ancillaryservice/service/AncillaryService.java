package com.portfolio.ancillaryservice.service;

import com.portfolio.payload.request.AncillaryRequest;
import com.portfolio.payload.response.AncillaryResponse;

import java.util.List;

public interface AncillaryService {
    AncillaryResponse createAncillary(Long airlineId, AncillaryRequest request);
    AncillaryResponse getById(Long id);
    List<AncillaryResponse> getByAirlineId(Long airlineId);
    AncillaryResponse updateAncillary(Long id, AncillaryRequest request);
    void deleteAncillary(Long id);
}
