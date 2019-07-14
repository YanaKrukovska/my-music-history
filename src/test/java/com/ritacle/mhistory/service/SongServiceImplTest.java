package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SongServiceImplTest {
    @Autowired
    SongService service;

    @Autowired
    SongRepository repository;

    @Test
    @Ignore
    public void save() {
        Song song = new Song("My Way", "Ava Max", "My Way");
        repository.findAll();
        service.save(song);
    }

}