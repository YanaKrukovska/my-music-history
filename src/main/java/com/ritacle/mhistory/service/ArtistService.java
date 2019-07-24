package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Artist;

public interface ArtistService {

    Artist findArtistByName(String name);

    Artist save (Artist artist);

    Artist getArtist (Long id);
}
