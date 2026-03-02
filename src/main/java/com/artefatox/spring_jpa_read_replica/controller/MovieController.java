package com.artefatox.spring_jpa_read_replica.controller;

import com.artefatox.spring_jpa_read_replica.entity.Movie;
import com.artefatox.spring_jpa_read_replica.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping
    public ResponseEntity<Page<Movie>> list(Pageable pageable) {
        Page<Movie> page = service.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        Movie movie = service.findById(id);
        return ResponseEntity.ok(movie);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody Movie movie) {
        Movie response = service.create(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
