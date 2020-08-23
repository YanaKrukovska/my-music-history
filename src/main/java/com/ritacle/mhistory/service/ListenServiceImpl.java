package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.*;
import com.ritacle.mhistory.persistence.repository.ListenRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class ListenServiceImpl implements ListenService {

    private final ListenRepository repository;

    private final SongService songService;

    private final ArtistService artistService;

    private final AlbumService albumService;

    private final UserService userService;

    @Autowired
    public ListenServiceImpl(ListenRepository repository, SongService songService, ArtistService artistService, AlbumService albumService, UserService userService) {
        this.repository = repository;
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
        this.userService = userService;
    }

    @Override
    public Response<Listen> addListen(Listen listen) {

        Response<Listen> nullCheckResponse = checkNullParameters(listen);
        if (!nullCheckResponse.getErrors().isEmpty()) {
            return new Response<>(listen, nullCheckResponse.getErrors());
        }

        List<InputError> errors = validateListen(listen);
        if (!errors.isEmpty()) {
            return new Response<>(listen, errors);
        }

        Song song = listen.getSong();

        Response<Artist> artistResponse = artistService.save(listen.getSong().getAlbum().getArtist());
        if (artistResponse.getErrors().isEmpty()) {
            song.getAlbum().setArtist(artistResponse.getObject());
        } else {
            return new Response<>(listen, artistResponse.getErrors());
        }

        Response<Album> albumResponse = albumService.save(song.getAlbum());
        if (albumResponse.getErrors().isEmpty()) {
            song.setAlbum(albumResponse.getObject());
        } else {
            return new Response<>(listen, albumResponse.getErrors());
        }

        Response<Song> songResponse = songService.save(song);
        if (songResponse.getErrors().isEmpty()) {
            listen.setSong(songResponse.getObject());
        } else {
            return new Response<>(listen, songResponse.getErrors());
        }


        User userDB = userService.findUserByMailIgnoreCase(listen.getUser().getMail());
        if (userDB == null) {
            Response<User> savedUser = userService.save(listen.getUser());
            if (savedUser.getErrors().isEmpty()) {
                listen.setUser(savedUser.getObject());
            } else {
                return new Response<>(listen, savedUser.getErrors());
            }
        } else {
            listen.setUser(userDB);
        }

        return new Response<>(repository.save(listen), errors);
    }

    private Response<Listen> checkNullParameters(Listen listen) {
        if (listen == null) {
            return new Response<>(null, new LinkedList<>(Collections.singleton(new InputError("listen", "Listen is required"))));
        }
        if (listen.getSong() == null) {
            return new Response<>(listen, new LinkedList<>(Collections.singleton(new InputError("song", "Song is required"))));
        }
        if (listen.getSong().getAlbum() == null) {
            return new Response<>(listen, new LinkedList<>(Collections.singleton(new InputError("album", "Album is required"))));
        }
        if (listen.getSong().getAlbum() != null && listen.getSong().getAlbum().getArtist() == null) {
            return new Response<>(listen, new LinkedList<>(Collections.singleton(new InputError("artist", "Artist is required"))));
        }
        return new Response<>(listen, new LinkedList<>());
    }

    private List<InputError> validateListen(Listen listen) {
        List<InputError> errors = new LinkedList<>();
        if (StringUtils.isAllBlank(listen.getSong().getTitle())) {
            errors.add(new InputError("songTitle", "Song title is required"));
        }
        if (StringUtils.isAllBlank(listen.getSong().getAlbum().getArtist().getName())) {
            errors.add(new InputError("artistName", "Artist name is required"));
        }
        if (StringUtils.isAllBlank(listen.getSong().getAlbum().getTitle())) {
            errors.add(new InputError("albumTitle", "Album title is required"));
        }
        return errors;
    }

    @Override
    public Listen getListen(Long id) {
        return repository.findListenById(id);
    }

    @Override
    public boolean checkIfExists(Long listenerId, Long syncId) {
        return repository.findByUserIdAndSyncId(listenerId, syncId) != null;
    }

    @Override
    public Response<Listen> deleteById(Long id) {
        Listen listenDB = repository.findListenById(id);
        if (listenDB != null) {
            repository.deleteById(id);
            return new Response<>(listenDB, new LinkedList<>());
        } else {
            return new Response<>(null, new LinkedList<>(Collections.singleton(new InputError("listen", "Listen doesn't exist"))));
        }
    }

    @Override
    public Response<Listen> editListen(Listen listen) {

        Response<Listen> nullCheckResponse = checkNullParameters(listen);
        if (!nullCheckResponse.getErrors().isEmpty()) {
            return new Response<>(listen, nullCheckResponse.getErrors());
        }

        List<InputError> errors = validateListen(listen);
        if (!errors.isEmpty()) {
            return new Response<>(listen, errors);
        }

        Listen listenDB = getListen(listen.getId());
        if (listenDB == null) {
            return new Response<>(listen, new LinkedList<>(Collections.singleton(new InputError("id", "Listen with such id doesn't exist"))));
        }

        listenDB.setSong(songService.save(listen.getSong()).getObject());
        Response<Listen> updatedListen = addListen(listenDB);
        return new Response<>(updatedListen.getObject(), updatedListen.getErrors());
    }
}
