package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.Response;

public interface ArtistService {

    Artist findArtistByName(String name);

    Response<Artist> save (Artist artist);

    Artist getArtist (Long id);
}
