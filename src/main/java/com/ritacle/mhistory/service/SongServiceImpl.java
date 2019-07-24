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

    @Autowired
    private AlbumService albumService;

    @Override
    public Song save(Song song) {

        song.setAlbum(albumService.save(song.getAlbum()));
        Song persistedSong = getSong(song);

        if (persistedSong == null) {
            persistedSong = repository.save(song);
        }
        return persistedSong;
    }

    @Override
    public Song getSong(Song song) {
        Objects.requireNonNull(song.getTitle(), "Title is required");
        Objects.requireNonNull(song.getAlbum(), "Album is required");
        Objects.requireNonNull(song.getAlbum().getArtist(), "Artist is required");
        return repository.findSongByTitleIgnoreCaseAndAlbum(song.getTitle(), song.getAlbum());
    }
}
