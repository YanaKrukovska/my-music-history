package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.SongStatistic;

public interface SongStatisticService {

    SongStatistic getSongStatistic(String listener, String title, String artist);
}
