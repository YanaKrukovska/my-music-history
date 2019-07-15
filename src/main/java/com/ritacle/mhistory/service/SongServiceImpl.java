package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService
{
    @Autowired
    private SongRepository repository;

    @Override
    public boolean checkIfExist(Song song) {
        return false;
    }

    @Override
    public Song save(Song song) {
        return repository.save(song);
    }
}
