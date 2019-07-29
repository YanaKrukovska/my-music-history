package com.ritacle.mhistory.service;


import com.ritacle.mhistory.persistence.model.stats.SongStats;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StatisticsService {

  List<SongStats> getSongsStats();

}
