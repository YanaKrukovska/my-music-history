package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Song;

public interface SongService {

    boolean checkIfExist(Song song);
    Song save(Song song);

}
