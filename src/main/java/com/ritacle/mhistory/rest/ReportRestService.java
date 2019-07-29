package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.stats.SongStats;
import com.ritacle.mhistory.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportRestService {

@Autowired
private StatisticsService statisticsService;


    @GetMapping("/songs")
    public List<SongStats> getSongReport() {
        return statisticsService.getSongsStats();
    }
}
