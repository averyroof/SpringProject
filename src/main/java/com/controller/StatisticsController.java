package com.controller;

import com.entity.Statistics;
import com.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    @ApiOperation(value = "Найти все статистики")
    List<Statistics> findAll() {
        List<Statistics> statistics = statisticsService.findAll();
        return statistics;
    }
}
