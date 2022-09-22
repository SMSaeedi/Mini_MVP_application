package com.at.check24.dto;

import com.at.check24.enums.GenreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieInDto {
    private GenreType genre;
    private String title;
    private Integer directorId;
    private String storyBy;
    private String summary;
}