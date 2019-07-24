package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository repository;

    @Override
    public Artist findArtistByName(String name) {
        return repository.findArtistByNameIgnoreCase(name);
    }

    @Override
    public Artist save(Artist artist) {
        Artist persistedArtist = findArtistByName(artist.getName());

        if (persistedArtist == null) {
            persistedArtist = repository.save(artist);
        }


        return persistedArtist;
    }

    @Override
    public Artist getArtist(Long id) {
        return repository.getOne(id);
    }
}
