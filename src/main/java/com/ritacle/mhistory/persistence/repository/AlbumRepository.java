package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
