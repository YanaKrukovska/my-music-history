package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class SongServiceImplTest {
    @Autowired
    SongService service;

    @Autowired
    SongRepository repository;

    @Test
    public void save() {
        Song song = new Song("My Way", "Ava Max", "My Way");
        Song savedSong = service.save(song);
        Assert.assertNotNull(savedSong.getId());
    }

}