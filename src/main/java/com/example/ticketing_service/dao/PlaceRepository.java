package com.example.ticketing_service.dao;

import com.example.ticketing_service.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
