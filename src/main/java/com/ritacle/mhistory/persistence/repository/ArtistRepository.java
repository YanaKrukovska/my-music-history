package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository  extends JpaRepository<Artist,Long> {

    Artist findArtistByNameIgnoreCase(String name);
}
