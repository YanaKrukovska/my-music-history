package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.InputError;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.repository.ArtistRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository repository;

    @Override
    public Artist findArtistByName(String name) {
        return repository.findArtistByNameIgnoreCase(name);
    }

    @Override
    public Response<Artist> save(Artist artist) {

        if (artist == null) {
            return new Response<>(null, new LinkedList<>(Collections.singleton(new InputError("artist", "Artist is required"))));
        }

        List<InputError> errors = validateArtist(artist);
        if (!errors.isEmpty()) {
            return new Response<>(artist, errors);
        }

        Artist persistedArtist = findArtistByName(artist.getName());
        if (persistedArtist == null) {
            persistedArtist = repository.save(artist);
        }


        return new Response<>(persistedArtist, errors);
    }

    private List<InputError> validateArtist(Artist artist) {
        List<InputError> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(artist.getName())) {
            errors.add(new InputError("songTitle", "Song title is required"));
        }
        return errors;
    }

    @Override
    public Artist getArtist(Long id) {
        return repository.getOne(id);
    }
}
