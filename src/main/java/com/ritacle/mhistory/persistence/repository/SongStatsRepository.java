package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.stats.SongStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongStatsRepository extends JpaRepository<SongStats, Long> {
    @Query("")
    List<SongStats> getSongStats();
}
