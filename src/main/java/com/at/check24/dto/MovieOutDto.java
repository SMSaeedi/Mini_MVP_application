package com.at.check24.dto;

import com.at.check24.enums.GenreType;
import com.at.check24.enums.RateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieOutDto implements Serializable {
    private GenreType genre;
    private String title;
    private Integer directorId;
    private String storyBy;
    private RateType rate;
    private String summary;
}
