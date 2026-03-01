package com.artefatox.spring_jpa_read_replica.repository;

import com.artefatox.spring_jpa_read_replica.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
