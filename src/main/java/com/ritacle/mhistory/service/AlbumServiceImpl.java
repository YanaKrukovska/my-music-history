package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.InputError;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.repository.AlbumRepository;
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

        if (album == null){
            return new Response<>(null, new LinkedList<>(Collections.singleton(new InputError("album", "Album is required"))));
        }

        Response<Artist> savedArtist = artistService.save(album.getArtist());
        if (savedArtist.getErrors().isEmpty()) {
           album.setArtist(savedArtist.getObject());
        } else {
            return new Response<>(album, savedArtist.getErrors());
        }

        Album persistedAlbum = findAlbumByTitleAndArtist(album.getTitle(), album.getArtist());

        if (persistedAlbum == null) {
            persistedAlbum = repository.save(album);
        }

        return new Response<>(persistedAlbum, new LinkedList<>());
    }

    @Override
    public Album getAlbum(Long id) {
        return repository.getOne(id);
    }

}
