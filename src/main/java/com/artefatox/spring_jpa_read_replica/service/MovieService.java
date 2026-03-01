package com.artefatox.spring_jpa_read_replica.service;

import com.artefatox.spring_jpa_read_replica.entity.Movie;
import com.artefatox.spring_jpa_read_replica.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    public Page<Movie> list(Pageable pageable) {
        log.info("Listando os filmes com paginação...");
        return repository.findAll(pageable);
    }

    public Movie create(Movie movie) {
        log.info("Criando um novo filme...");
        return repository.save(movie);
    }

    public Movie findById(Long id) {
        log.info("Buscando um filme pelo id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
