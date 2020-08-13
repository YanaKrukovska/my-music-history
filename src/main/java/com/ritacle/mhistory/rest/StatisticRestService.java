package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.stats.SongStatistic;
import com.ritacle.mhistory.service.SongStatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatisticRestService {

    Logger logger = LoggerFactory.getLogger(SongStatisticService.class);

    private final SongStatisticService statisticsService;

    @Autowired
    public StatisticRestService(SongStatisticService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{listener}/count/{artist}/{title}")
    public SongStatistic getSongCount(@PathVariable String listener, @PathVariable String artist, @PathVariable String title) {
        logger.debug("Getting count of listen for user:{} [{} - {})", listener, artist, title);
        return statisticsService.getSongStatistic(listener, title, artist);
    }
}
