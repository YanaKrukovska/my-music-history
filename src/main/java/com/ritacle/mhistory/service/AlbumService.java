package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;

public interface AlbumService {

    Album findAlbumByTitleAndArtist (String title, Artist artist);

    Album save(Album album);

    Album getAlbum (Long id);
}
