package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.SongStats;
import com.ritacle.mhistory.persistence.repository.SongStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    SongStatsRepository repository;



    @Override
    public List<SongStats> getUserListens(String userMail, Sort sort) {

        return repository.findFirst30ByUserMailIgnoreCase(userMail, sort);
    }


}