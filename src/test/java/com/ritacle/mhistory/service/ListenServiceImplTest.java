package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.persistence.model.Song;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import com.ritacle.mhistory.persistence.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class ListenServiceImplTest {

    @Autowired
    ListenService service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Test
    public void addListenExistingSong() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho", "Ava Max", "Sweet But Psycho");
        listen.setSong(song);
        listen.setUser(userRepository.getOne(1L));
        listen.setListenDate(new Date());

        Listen result = service.addListen(listen);
        assertEquals("Sweet But Psycho", songRepository.findById(result.getSong().getId()).get().getTitle() );
    }
    @Test
    public void addListenNotExistingSong() {
        Listen listen = new Listen();
        Song song = new Song("Skibidi", "Little Big", "Antipositive, Pt. 2");
        listen.setSong(song);
        listen.setUser(userRepository.getOne(2L));
        listen.setListenDate(new Date());

        Listen result = service.addListen(listen);
        assertNotNull(result.getId());
        assertNotNull(result.getSong().getId());
        assertEquals("Skibidi", songRepository.findById(result.getSong().getId()).get().getTitle() );

    }


}