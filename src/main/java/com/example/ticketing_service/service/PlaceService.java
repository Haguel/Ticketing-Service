package com.example.ticketing_service.service;

import com.example.ticketing_service.dao.PlaceRepository;
import com.example.ticketing_service.dto.PlaceDTO;
import com.example.ticketing_service.model.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Place createPlace(PlaceDTO placeDTO) {
        Place place = new Place(placeDTO.getAddress(), placeDTO.getName());

        placeRepository.save(place);

        return place;
    }
}
