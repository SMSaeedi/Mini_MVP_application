package com.at.check24.service;

import com.at.check24.dto.MovieOutDto;
import com.at.check24.enums.GenreType;
import com.at.check24.enums.RateType;
import com.at.check24.model.Director;
import com.at.check24.model.Movie;
import com.at.check24.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieTest {
    @Autowired
    private MovieService movieService;

    @MockBean
    MovieRepository movieRepository;

    private List<Movie> movies() {
        List<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie(1, GenreType.DRAMA, "Pride & Prejudice", director().get(0).getId(), "Jane Austen", RateType.FOUR, "Mrs Bennet insists that her daughters find rich husbands and settle down. When a wealthy bachelor starts living near them, Mrs Bennet's happiness knows no bounds."));
        movieList.add(new Movie(2, GenreType.DRAMA, "The Shawshank Redemption", director().get(1).getId(), "Stephen King", RateType.FIVE, "Andy Dufresne, a successful banker, is arrested for the murders of his wife and her lover, and is sentenced to life imprisonment at the Shawshank prison. He becomes the most unconventional prisoner."));
        movieList.add(new Movie(3, GenreType.DRAMA, "Friends", director().get(2).getId(), "", RateType.FOUR, "Follow the lives of six reckless adults living in Manhattan, as they indulge in adventures which make their lives both troublesome and happening."));

        return movieList;
    }

    private List<Director> director() {
        List<Director> directors = new ArrayList<>();

        directors.add(new Director(1, "Joe Wright"));
        directors.add(new Director(2, "Frank Darabont"));
        directors.add(new Director(3, "XX"));

        return directors;
    }

    @Test
    public void movieEntityWithZeroSize() {
        when(movieRepository.count()).thenReturn(0L);

        assertEquals(0L, movieRepository.count());
        verify(movieRepository).count();
    }

    @Test
    public void movieEntitySizeCount() {
        when(movieRepository.save(movies().get(0))).thenReturn(movies().get(0));
        when(movieRepository.count()).thenReturn(1L);

        assertEquals(1L, movieRepository.count());
        verify(movieRepository).count();
    }

//    @Test
    public void getMovieByTitle() {
        when(movieRepository.findMovieByTitle("Friends"))
                .thenReturn(Optional.of(movies().get(2)));
        MovieOutDto movieOutDto = movieService.searchMovieByTitle("Friends");

        assertEquals(movies().get(2).getTitle(), movieOutDto.getTitle());
    }

    @Test
    public void findAllMovies() {
        when(movieRepository.findAll()).thenReturn(movies());
        when(movieRepository.count()).thenReturn(3L);

        List<MovieOutDto> movieOutDtoList = movieService.fetchAllMovies();

        assertEquals(movieRepository.count(), movieOutDtoList.size());
        verify(movieRepository).count();
    }
}