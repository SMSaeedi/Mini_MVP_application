package com.at.check24.dto;

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
public class RateDto implements Serializable {
    private Integer userId;
    private Integer movieId;
    private RateType rate;
}