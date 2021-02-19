package com.example.work.input;

import com.example.work.geolocation.requestbody.Coordinates;
import com.example.work.geolocation.service.GeolocationService;
import com.example.work.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/coordinates")
public class GeolocationRestController {

    private final GeolocationService geolocationService;

    @PutMapping
    public void updateLocation(@RequestBody Coordinates coordinates, Authentication authentication) {
        geolocationService.updateLocation(coordinates, ((SecurityUser)authentication.getPrincipal()).getId());
    }
}
