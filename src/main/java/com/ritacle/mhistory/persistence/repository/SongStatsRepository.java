package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.stats.SongStats;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongStatsRepository extends JpaRepository<SongStats, Long> {


    List<SongStats> findFirst30ByUserMailIgnoreCase(String userMail, Sort sort);
}
