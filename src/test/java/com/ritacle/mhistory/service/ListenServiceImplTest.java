package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.*;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import com.ritacle.mhistory.persistence.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class ListenServiceImplTest {

    @Autowired
    ListenService service;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Test
    @Ignore
    public void addListenExistingSong() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho",  new Album("Sweet But Psycho",new Artist("Ava Max")));
        listen.setSong(song);
        listen.setUser(userRepository.getOne(1L));
        listen.setListenDate(new Date());

        Listen result = service.addListen(listen);
        assertEquals("Sweet But Psycho", songRepository.findById(result.getSong().getId()).get().getTitle());
    }


    @Test
    @Ignore
    public void addListenNotExistingSong() {
        Listen listen = new Listen();
        Song song = new Song("Skibidi",  new Album( "Antipositive, Pt. 2", new Artist( "Little Big")));
        listen.setSong(song);
        listen.setUser(userRepository.getOne(2L));
        listen.setListenDate(new Date());

        Listen result = service.addListen(listen);
        assertNotNull(result.getId());
        assertNotNull(result.getSong().getId());
        assertEquals("Skibidi", songRepository.findById(result.getSong().getId()).get().getTitle());

    }


    @Test
    public void addListenExistingUser() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho", new Album("Sweet But Psycho", new Artist("Ava Max")));
        listen.setSong(song);
        listen.setUser(userService.save( new User("v.krukovskyy@gmail.com")));
        listen.setListenDate(new Date());

        Listen result = service.addListen(listen);
        assertEquals("Sweet But Psycho", songRepository.findById(result.getSong().getId()).get().getTitle());
        assertEquals("v.krukovskyy@gmail.com", result.getUser().getMail());
    }


    @Test
    public void addListenNotExistingUser() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho",  new Album("Sweet But Psycho",new Artist("Ava Max")));
        listen.setSong(song);
        listen.setUser(userService.save(  new User("Test", "Test", "test@gmail.com", "", "F", new Date(2019-07-31))));
        listen.setListenDate(new Date());

        Listen result = service.addListen(listen);
        assertEquals("test@gmail.com", result.getUser().getMail());
    }


}