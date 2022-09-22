package com.at.check24.controller;

import com.at.check24.dto.MovieOutDto;
import com.at.check24.dto.RateDto;
import com.at.check24.dto.UserDto;
import com.at.check24.enums.GenreType;
import com.at.check24.enums.JobType;
import com.at.check24.enums.RateType;
import com.at.check24.service.MovieService;
import com.at.check24.service.RateService;
import com.at.check24.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class RateTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RateService rateService;

    @MockBean
    private MovieService movieService;

    @MockBean
    private UserService userService;

    private RateDto rate() {
        return RateDto.builder()
                .movieId(1)
                .userId(1)
                .rate(RateType.FOUR)
                .build();
    }

    private MovieOutDto movie() {
        return MovieOutDto.builder()
                .rate(RateType.ZERO)
                .genre(GenreType.DRAMA)
                .directorId(2)
                .title("Sense and Sensibility")
                .storyBy("Jane Austen")
                .summary("A classic drama story")
                .build();
    }

    private UserDto user() {
        return UserDto.builder()
                .job(JobType.CRITICISM)
                .name("Sarah")
                .build();
    }

    @Test
    public void getMovieByTitle() throws Exception {
        when(rateService.rateToMovie(rate().getUserId(), rate().getMovieId(), rate().getRate())).thenReturn((rate()));
        when(movieService.findMovieById(rate().getMovieId())).thenReturn(movie());
        when(userService.findUserById(rate().getUserId())).thenReturn(user());
        mockMvc.perform(post("/api/rates/{userId}/{movieId}/{rateNr}", rate().getUserId(), rate().getMovieId(), rate().getRate()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}