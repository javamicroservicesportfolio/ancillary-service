package com.portfolio.ancillaryservice.service.impl;

import com.portfolio.ancillaryservice.mapper.AncillaryMapper;
import com.portfolio.ancillaryservice.model.Ancillary;
import com.portfolio.ancillaryservice.repository.AncillaryRepository;
import com.portfolio.ancillaryservice.service.AncillaryService;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.payload.request.AncillaryRequest;
import com.portfolio.payload.response.AncillaryResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AncillaryServiceImpl implements AncillaryService {
    private final AncillaryRepository ancillaryRepository;

    @Override
    public AncillaryResponse createAncillary(Long airlineId, AncillaryRequest request) {
        Ancillary ancillary = Ancillary.builder()
                .type(request.getType())
                .subType(request.getSubType())
                .rfisc(request.getRfisc())
                .name(request.getName())
                .description(request.getDescription())
                .metadata(request.getMetadata())
                .displayOrder(request.getDisplayOrder())
                .airlineId(airlineId)
                .build();

        ancillaryRepository.save(ancillary);

        return AncillaryMapper.toResponse(ancillary, List.of());
    }

    @Override
    public AncillaryResponse getById(Long id) {
        Ancillary response = ancillaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ancillary not found with id: " + id));

        // TODO: 11/08/2026 Fetch coverages for the ancillary and map them to InsuranceCoverageResponse


        return AncillaryMapper.toResponse(response, List.of());
    }

    @Override
    public List<AncillaryResponse> getByAirlineId(Long airlineId) {
        return ancillaryRepository.findByAirlineId(airlineId).stream()
                .map(
                        ancillary -> {
                            // TODO: 11/08/2026 Fetch coverages for the ancillary and map them to InsuranceCoverageResponse

                            return AncillaryMapper.toResponse(ancillary, List.of());
                        }
                )
                .toList();
    }

    @Override
    public AncillaryResponse updateAncillary(Long id, AncillaryRequest request) {
        Ancillary ancillary = ancillaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ancillary not found with id: " + id));

        // Update the ancillary fields
        ancillary.setType(request.getType());
        ancillary.setSubType(request.getSubType());
        ancillary.setRfisc(request.getRfisc());
        ancillary.setName(request.getName());
        ancillary.setDescription(request.getDescription());
        ancillary.setMetadata(request.getMetadata());
        ancillary.setDisplayOrder(request.getDisplayOrder());

        ancillaryRepository.save(ancillary);

        return AncillaryMapper.toResponse(ancillary, List.of());
    }

    @Override
    public void deleteAncillary(Long id) {
        Ancillary ancillary = ancillaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ancillary not found with id: " + id));
        ancillaryRepository.delete(ancillary);
    }
}
