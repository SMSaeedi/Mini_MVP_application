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
import java.util.stream.Collectors;

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
        Movie save = movieRepository.save(movie);
        return mapper.convertValue(save, MovieOutDto.class);
    }

    public MovieOutDto updateMovie(Integer movieId, RateType rateNr) {
        Movie fetchMovie = movieRepository.findById(movieId).filter(movie -> movie.getId().equals(movieId)).stream().findFirst().orElseThrow();
        fetchMovie.setRate(rateNr);
        return mapper.convertValue(movieRepository.save(fetchMovie), MovieOutDto.class);
    }

    public MovieOutDto searchMovieByTitle(String title) {
        return findMoviesByTiles(title).stream().findFirst().orElseThrow(() -> new NotFoundException(movieNotFound));
    }

    public List<MovieOutDto> findMoviesByTiles(String title) {
        return movieRepository.findAllByTitleOrderByRateDescIdAsc(title)
                .stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MovieOutDto findMovieById(Integer movieId) {
        return movieRepository.findById(movieId).map(this::convertToDto).orElseThrow(() -> new NotFoundException(movieNotFound));
    }

    public List<MovieOutDto> fetchAllMovies() {
        return movieRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private MovieOutDto convertToDto(Movie movie) {
        return mapper.convertValue(movie, MovieOutDto.class);
    }
}