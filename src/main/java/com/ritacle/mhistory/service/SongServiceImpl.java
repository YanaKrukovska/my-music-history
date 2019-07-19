package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository repository;


    @Override
    public Song save(Song song) {
        return repository.save(song);
    }

    @Override
    public Song getSong(Song song) {
        Objects.requireNonNull(song.getTitle(), "Title is required");
        Objects.requireNonNull(song.getArtist(), "Artist is required");
        Objects.requireNonNull(song.getAlbum(), "Album is required");
        return repository.findSongByTitleIgnoreCaseAndArtistAndAlbum(song.getTitle(), song.getArtist(), song.getAlbum());
    }
}
