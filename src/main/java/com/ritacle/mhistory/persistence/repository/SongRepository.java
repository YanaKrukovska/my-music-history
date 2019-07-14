package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository <Song,Long> {
}
