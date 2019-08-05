package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.SongStats;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StatisticsService {



  List<SongStats> getUserListens(String userMail, Sort sort);
}
