package com.at.check24.model;

import com.at.check24.enums.GenreType;
import com.at.check24.enums.RateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private GenreType genre;
    private String title;
    private Integer directorId;
    private String storyBy;
    private RateType rate;
    private String summary;
}
