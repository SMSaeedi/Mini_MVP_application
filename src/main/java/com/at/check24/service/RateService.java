package com.at.check24.service;

import com.at.check24.dto.MovieOutDto;
import com.at.check24.dto.RateDto;
import com.at.check24.enums.RateType;
import com.at.check24.model.Rate;
import com.at.check24.repository.RateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class RateService {

    private final RateRepository rateRepository;
    private final UserService userService;
    private final MovieService movieService;
    private final ObjectMapper mapper;

    public RateService(RateRepository rateRepository, UserService userService, MovieService movieService, ObjectMapper mapper) {
        this.rateRepository = rateRepository;
        this.userService = userService;
        this.movieService = movieService;
        this.mapper = mapper;
    }

    public RateDto rateToMovie(Integer userId, Integer movieId, RateType rateNr) {
        userService.findUserById(userId);

        Rate save = rateRepository.save(Rate.builder()
                .movieId(movieId)
                .userId(userId)
                .rate(rateNr)
                .build());

        movieService.updateMovie(movieId, rateNr);

        return mapper.convertValue(save, RateDto.class);
    }
}