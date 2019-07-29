package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.SongStats;
import com.ritacle.mhistory.persistence.repository.SongStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    SongStatsRepository repository;

    @Override
    public List<SongStats> getSongsStats() {
        return repository.getSongStats();
    }
}
