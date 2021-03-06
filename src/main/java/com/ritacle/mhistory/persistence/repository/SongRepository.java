package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository <Song,Long> {

    Song findSongByTitleIgnoreCaseAndAlbum (String title,  Album album);


}
