package com.at.check24.service;

import com.at.check24.dto.RateDto;
import com.at.check24.enums.GenreType;
import com.at.check24.enums.JobType;
import com.at.check24.enums.RateType;
import com.at.check24.model.Movie;
import com.at.check24.model.Rate;
import com.at.check24.model.User;
import com.at.check24.repository.MovieRepository;
import com.at.check24.repository.RateRepository;
import com.at.check24.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RateTest {
    @Autowired
    private RateService rateService;

    @MockBean
    MovieRepository movieRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RateRepository rateRepository;

    private List<Movie> movies() {
        List<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie(1, GenreType.DRAMA, "Pride & Prejudice", 0, "Jane Austen", RateType.FOUR, "Mrs Bennet insists that her daughters find rich husbands and settle down. When a wealthy bachelor starts living near them, Mrs Bennet's happiness knows no bounds."));
        movieList.add(new Movie(2, GenreType.DRAMA, "The Shawshank Redemption", 1, "Stephen King", RateType.FIVE, "Andy Dufresne, a successful banker, is arrested for the murders of his wife and her lover, and is sentenced to life imprisonment at the Shawshank prison. He becomes the most unconventional prisoner."));
        movieList.add(new Movie(1, GenreType.DRAMA, "Friends", 2, "", RateType.FOUR, "Follow the lives of six reckless adults living in Manhattan, as they indulge in adventures which make their lives both troublesome and happening."));

        return movieList;
    }

    private Rate rate() {
        return Rate.builder()
                .movieId(movies().get(0).getId())
                .userId(1)
                .rate(RateType.FOUR)
                .build();
    }

    private Movie movie() {
        return Movie.builder()
                .id(rate().getMovieId())
                .rate(rate().getRate())
                .build();
    }

    private User user() {
        return User.builder()
                .job(JobType.CRITICISM)
                .name("Sarah")
                .build();
    }

    @Test
    public void rateToAMovie() {
        when(rateRepository.save(rate())).thenReturn(rate());
        when(movieRepository.save(movie())).thenReturn(movie());
        when(movieRepository.findById(rate().getMovieId())).thenReturn(Optional.of(movie()));
        when(userRepository.findById(rate().getUserId())).thenReturn(Optional.of(user()));

        RateDto rateDto = rateService.rateToMovie(1, rate().getMovieId(), RateType.FOUR);

        assertEquals(rate().getRate(), rateDto.getRate());
        assertEquals(rate().getMovieId(), movies().get(0).getId());
        assertEquals(rate().getUserId(), rateDto.getUserId());
    }
}