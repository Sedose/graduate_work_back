package com.example.work.geolocation.service;

import com.example.work.entity.UserCoordinatesEntity;
import com.example.work.geolocation.repository.GeolocationRepository;
import com.example.work.geolocation.requestbody.Coordinates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeolocationService {

    private final GeolocationRepository geolocationRepository;

    public void updateLocation(Coordinates coordinates, Long userId) {
        geolocationRepository.save(new UserCoordinatesEntity(
                userId, coordinates.getLatitude(), coordinates.getLongitude(), !geolocationRepository.existsById(userId)
        ));
    }
}
