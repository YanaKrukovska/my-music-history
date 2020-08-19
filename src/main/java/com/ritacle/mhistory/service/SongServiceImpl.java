package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.InputError;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository repository;

    @Autowired
    private AlbumService albumService;

    @Override
    public Response<Song> save(Song song) {

        if (song == null) {
            return new Response<>(null, new LinkedList<>(Collections.singleton(new InputError("song", "Song is required"))));
        }

        List<InputError> errors = validateSong(song);
        if (!errors.isEmpty()) {
            return new Response<>(song, errors);
        }

        Response<Album> albumResponse = albumService.save(song.getAlbum());
        if (albumResponse.getErrors().isEmpty()) {
            song.setAlbum(albumResponse.getObject());
        } else {
            return new Response<>(song, albumResponse.getErrors());
        }

        Song persistedSong = getSong(song);
        if (persistedSong == null) {
            persistedSong = repository.save(song);
        }

        return new Response<>(persistedSong, new LinkedList<>());
    }

    private List<InputError> validateSong(Song song) {
        List<InputError> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(song.getTitle())) {
            errors.add(new InputError("songTitle", "Song title is required"));
        }
        return errors;
    }

    @Override
    public Song getSong(Song song) {
        return repository.findSongByTitleIgnoreCaseAndAlbum(song.getTitle(), song.getAlbum());
    }
}
