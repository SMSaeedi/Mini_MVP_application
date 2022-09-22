package com.at.check24.controller;

import com.at.check24.dto.RateDto;
import com.at.check24.enums.RateType;
import com.at.check24.service.RateService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rates")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("/{userId}/{movieId}/{rateNr}")
    RateDto rateToMovie(@PathVariable Integer userId,
                        @PathVariable Integer movieId,
                        @PathVariable RateType rateNr) {
        return rateService.rateToMovie(userId, movieId, rateNr);
    }
}