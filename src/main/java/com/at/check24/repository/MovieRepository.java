package com.at.check24.repository;

import com.at.check24.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findMovieByTitle(String title);
}