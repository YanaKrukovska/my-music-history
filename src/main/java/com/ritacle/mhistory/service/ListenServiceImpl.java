package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.ListenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ListenServiceImpl implements ListenService {

    @Autowired
    ListenRepository repository;

    @Autowired
    SongService songService;

    @Autowired
    ArtistService artistService;

    @Autowired
    AlbumService albumService;

    @Override
    public Listen addListen(Listen listen) {
        validateListen(listen);

        Song song = listen.getSong();


        song.getAlbum().setArtist( artistService.save(listen.getSong().getAlbum().getArtist()));
        song.setAlbum(albumService.save(song.getAlbum()));
        listen.setSong(songService.save(song));


        return repository.save(listen);
    }

    private void validateListen(Listen listen) {
        Objects.requireNonNull(listen, "Listen is required");
        Objects.requireNonNull(listen.getSong(), "Song is required");
        Objects.requireNonNull(listen.getSong().getAlbum().getArtist(), "Artist is required");
        Objects.requireNonNull(listen.getSong().getAlbum().getArtist().getName(), "Artist name is required");
        Objects.requireNonNull(listen.getSong().getAlbum(), "Album is required");
        Objects.requireNonNull(listen.getSong().getAlbum().getTitle(), "Album name is required");
    }

    @Override
    public Listen getListen(Long id) {
        return repository.getOne(id);
    }
}
