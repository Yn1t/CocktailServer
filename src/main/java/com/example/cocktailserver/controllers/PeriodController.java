package com.example.cocktailserver.controllers;

import com.example.cocktailserver.controllers.exceptions.PeriodAlreadyExistsException;
import com.example.cocktailserver.controllers.models.PeriodRequest;
import com.example.cocktailserver.services.PeriodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/period")
public class PeriodController {

    private final PeriodService periodService;

    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostMapping("/add")
    public String addPeriod(@RequestBody PeriodRequest periodRequest) throws PeriodAlreadyExistsException {
        return periodService.addPeriod(periodRequest);
    }

    @PostMapping("/add_periods")
    public @ResponseBody Map<String,List<String>> addPeriods(@RequestBody Map<String,List<PeriodRequest>> periods)
            throws PeriodAlreadyExistsException {
        return periodService.addPeriods(periods.get("periods"));
    }
}
