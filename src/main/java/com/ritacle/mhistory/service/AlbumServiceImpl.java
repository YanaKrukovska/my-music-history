package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.InputError;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.repository.AlbumRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository repository;

    @Autowired
    private ArtistService artistService;

    @Override
    public Album findAlbumByTitleAndArtist(String title, Artist artist) {
        return repository.findAlbumByTitleIgnoreCaseAndArtist(title, artist);
    }

    @Override
    public Response<Album> save(Album album) {

        if (album == null) {
            return new Response<>(null, new LinkedList<>(Collections.singleton(new InputError("album", "Album is required"))));
        }

        List<InputError> errors = validateAlbum(album);
        if (!errors.isEmpty()) {
            return new Response<>(album, errors);
        }

        Response<Artist> artistResponse = artistService.save(album.getArtist());
        if (artistResponse.getErrors().isEmpty()) {
            album.setArtist(artistResponse.getObject());
        } else {
            return new Response<>(album, artistResponse.getErrors());
        }

        Album persistedAlbum = findAlbumByTitleAndArtist(album.getTitle(), album.getArtist());
        if (persistedAlbum == null) {
            persistedAlbum = repository.save(album);
        }

        return new Response<>(persistedAlbum, new LinkedList<>());
    }

    private List<InputError> validateAlbum(Album album) {
        List<InputError> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(album.getTitle())) {
            errors.add(new InputError("albumTitle", "Album title is required"));
        }
        return errors;
    }

    @Override
    public Album getAlbum(Long id) {
        return repository.getOne(id);
    }

}
