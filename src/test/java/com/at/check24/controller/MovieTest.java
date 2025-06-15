package com.at.check24.controller;

import com.at.check24.dto.MovieOutDto;
import com.at.check24.enums.GenreType;
import com.at.check24.enums.RateType;
import com.at.check24.exception.NotFoundException;
import com.at.check24.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class MovieTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private List<MovieOutDto> movies() {
        List<MovieOutDto> movieList = new ArrayList<>();

        movieList.add(new MovieOutDto(GenreType.DRAMA, "Pride & Prejudice", 0, "Jane Austen", RateType.FOUR, "Mrs Bennet insists that her daughters find rich husbands and settle down. When a wealthy bachelor starts living near them, Mrs Bennet's happiness knows no bounds."));
        movieList.add(new MovieOutDto(GenreType.DRAMA, "The Shawshank Redemption", 1, "Stephen King", RateType.FIVE, "Andy Dufresne, a successful banker, is arrested for the murders of his wife and her lover, and is sentenced to life imprisonment at the Shawshank prison. He becomes the most unconventional prisoner."));
        movieList.add(new MovieOutDto(GenreType.DRAMA, "Friends Reunion",2, "", RateType.FOUR, "Follow the lives of six reckless adults living in Manhattan, as they indulge in adventures which make their lives both troublesome and happening."));

        return movieList;
    }

    @Test
    public void getMovieByTitle() throws Exception {
        when(movieService.searchMovieByTitle("Friends")).thenReturn((movies().get(2)));
        mockMvc.perform(get("/api/movies").pathInfo("/Friends"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getMovieByNotExistedName_throwsNotFoundException() throws Exception {
        when(movieService.searchMovieByTitle("Sherlock")).thenThrow(new NotFoundException("movie not found!"));
        mockMvc.perform(get("/api/movies/Sherlock"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void findAllMovies() throws Exception {
        when(movieService.fetchAllMovies()).thenReturn(movies());
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}