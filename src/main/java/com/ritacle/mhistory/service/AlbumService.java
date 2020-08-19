package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.Response;

public interface AlbumService {

    Album findAlbumByTitleAndArtist(String title, Artist artist);

    Response<Album> save(Album album);

    Album getAlbum(Long id);
}
