package com.at.check24.service;

import com.at.check24.dto.MovieInDto;
import com.at.check24.dto.MovieOutDto;
import com.at.check24.enums.RateType;
import com.at.check24.exception.NotFoundException;
import com.at.check24.model.Movie;
import com.at.check24.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Value("${service.movie.exception.notFound}")
    String movieNotFound;
    private final MovieRepository movieRepository;
    private final ObjectMapper mapper;

    public MovieService(MovieRepository movieRepository, ObjectMapper mapper) {
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    public MovieOutDto addNewMovie(MovieInDto newMovie) {
        Movie movie = mapper.convertValue(newMovie, Movie.class);
        movie.setRate(RateType.ZERO);
        return mapper.convertValue(movieRepository.save(movie), MovieOutDto.class);
    }

    public MovieOutDto searchMovieByTitle(String title) {
        Optional<Movie> findFilmByTitle = movieRepository.findMovieByTitle(title);

        if (!findFilmByTitle.isPresent())
            throw new NotFoundException(movieNotFound);

        return mapper.convertValue(findFilmByTitle, MovieOutDto.class);
    }

    public MovieOutDto findMovieById(Integer movieId) {
        Movie findMovieById = movieRepository.findById(movieId).orElseThrow(() -> new NotFoundException(movieNotFound));

        return mapper.convertValue(findMovieById, MovieOutDto.class);
    }

    public List<MovieOutDto> fetchAllMovies() {
        return mapper.convertValue(movieRepository.findAll(), List.class);
    }
}