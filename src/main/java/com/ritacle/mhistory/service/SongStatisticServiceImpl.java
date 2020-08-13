package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.SongStatistic;
import com.ritacle.mhistory.persistence.repository.SongStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongStatisticServiceImpl implements SongStatisticService {

    @Autowired
    SongStatisticRepository songStatisticRepository;

    @Override
    public SongStatistic getSongStatistic(String listener, String title, String artist) {
        return songStatisticRepository.findByListenerIgnoreCaseAndTitleIgnoreCaseAndArtistIgnoreCase(listener, title, artist);
    }
}
