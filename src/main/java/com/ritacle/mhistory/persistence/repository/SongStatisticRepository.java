package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.stats.SongStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongStatisticRepository extends JpaRepository<SongStatistic, Long> {

    SongStatistic findByListenerIgnoreCaseAndTitleIgnoreCaseAndArtistIgnoreCase(String listener, String title, String artist);
}
