package com.at.check24.controller;

import com.at.check24.dto.MovieInDto;
import com.at.check24.dto.MovieOutDto;
import com.at.check24.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MovieOutDto addNewMovie(@RequestBody MovieInDto newMovie) {
        return movieService.addNewMovie(newMovie);
    }

    @GetMapping("/{title}")
    MovieOutDto searchMovieByTitle(@PathVariable String title) {
        return movieService.searchMovieByTitle(title);
    }

    @GetMapping
    List<MovieOutDto> allMovies() {
        return movieService.fetchAllMovies();
    }
}