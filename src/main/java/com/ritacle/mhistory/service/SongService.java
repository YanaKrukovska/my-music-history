package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.model.Song;

public interface SongService {

    Response<Song> save(Song song);

    Song getSong(Song song);

}
