package com.ritacle.mhistory.service;

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

    @Override
    public Listen addListen(Listen listen) {
        Objects.requireNonNull(listen, "Listen is required");
        Song song = songService.getSong(listen.getSong());
        if (song == null) {
          song =  songService.save(listen.getSong());
        }
            listen.setSong(song);

        return repository.save(listen);
    }

    @Override
    public Listen getListen(Long id) {
        return repository.getOne(id);
    }
}
