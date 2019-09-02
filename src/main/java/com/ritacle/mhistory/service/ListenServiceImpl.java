package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.ListenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ListenServiceImpl implements ListenService {

    private ListenRepository repository;

    private SongService songService;

    private ArtistService artistService;

    private AlbumService albumService;


    @Autowired
    public ListenServiceImpl(ListenRepository repository, SongService songService, ArtistService artistService, AlbumService albumService) {
        this.repository = repository;
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
    }


    @Override
    public Listen addListen(Listen listen) {
        validateListen(listen);

        Song song = listen.getSong();
        song.getAlbum().setArtist(artistService.save(listen.getSong().getAlbum().getArtist()));
        song.setAlbum(albumService.save(song.getAlbum()));
        listen.setSong(songService.save(song));

        // listen.setUser(userService.save(listen.getUser()));

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

    @Override
    public boolean checkIfExists(Long listenerId, Long syncId) {
        return repository.findByUserIdAndSyncId(listenerId, syncId) != null;
    }
}
