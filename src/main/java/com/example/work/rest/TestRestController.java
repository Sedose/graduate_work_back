package com.example.work.rest;

import com.example.work.exception.SomeEntityNotFoundException;
import com.example.work.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/developers")
public class TestRestController {

    private final List<Developer> developers = new ArrayList<>() {{
        addAll(List.of(
                Developer.builder()
                        .id(1L)
                        .firstName("Sarah")
                        .lastName("Jones")
                        .build(),
                Developer.builder()
                        .id(2L)
                        .firstName("Evan")
                        .lastName("Lu")
                        .build(),
                Developer.builder()
                        .id(3L)
                        .firstName("Sobaken")
                        .lastName("Jowaken")
                        .build()
        ));
    }};

    @GetMapping
    public List<Developer> getAll() {
        return developers;
    }

    @GetMapping("/id")
    @PreAuthorize("hasAuthority('developers:read')")
    public Developer getById(@PathVariable Long id) {
        return developers.stream()
                .filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElseThrow(SomeEntityNotFoundException::new);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public Developer create(@RequestBody Developer developer) {
        developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteById(@PathVariable Long id) {
        developers.removeIf(developer -> developer.getId().equals(id));
    }
}
