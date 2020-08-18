package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.*;
import com.ritacle.mhistory.persistence.repository.SongRepository;
import com.ritacle.mhistory.persistence.repository.UserRepository;
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
    ListenService listenService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;


    @Test
    public void addListenExistingSong() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho", new Album("Sweet But Psycho", new Artist("Ava Max")));
        listen.setSong(song);
        listen.setUser(userRepository.findUserById(1L));
        listen.setListenDate(new Date());
        listen.setSyncId(8L);

        Listen result = listenService.addListen(listen).getObject();
        assertEquals("Sweet But Psycho", songRepository.findById(result.getSong().getId()).get().getTitle());
    }


    @Test
    public void addListenNotExistingSong() {
        Listen listen = new Listen();
        Song song = new Song("Skibidi", new Album("Antipositive, Pt. 2", new Artist("Little Big")));
        listen.setSong(song);
        listen.setUser(userRepository.findUserById(2L));
        listen.setListenDate(new Date());
        listen.setSyncId(9L);

        Listen result = listenService.addListen(listen).getObject();
        assertNotNull(result.getId());
        assertNotNull(result.getSong().getId());
        assertEquals("Skibidi", songRepository.findById(result.getSong().getId()).get().getTitle());

    }


    @Test
    public void addListenExistingUser() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho", new Album("Sweet But Psycho", new Artist("Ava Max")));
        listen.setSong(song);
        listen.setUser(new User("Slava", "Slava", "v.krukovskyy@gmail.com", "", "", "M", new Date(1978 - 11 - 13),  new Country("Ukraine", "UA")));
        listen.setListenDate(new Date());
        listen.setSyncId(10L);

        Listen result = listenService.addListen(listen).getObject();
        assertEquals("Sweet But Psycho", songRepository.findById(result.getSong().getId()).get().getTitle());
        assertEquals("v.krukovskyy@gmail.com", result.getUser().getMail());
    }


    @Test
    public void addListenNotExistingUser() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho", new Album("Sweet But Psycho", new Artist("Ava Max")));
        listen.setSong(song);
        listen.setUser(new User("Test", "Test", "test@gmail.com", "1234", "1234", "F", new Date(2019 - 07 - 31), new Country("Australia", "AU")));
        listen.setListenDate(new Date());
        listen.setSyncId(11L);

        Listen result = listenService.addListen(listen).getObject();
        assertEquals("test@gmail.com", result.getUser().getMail());
    }


    @Test
    public void checkIfExistsExistingSong() {
        assertTrue(listenService.checkIfExists(2L, 1L));
    }


    @Test
    public void checkIfExistsNotExistingSong() {
        assertFalse(listenService.checkIfExists(1L, 2L));
    }

    @Test
    public void deleteExistingListen() {
        Listen listen = new Listen();
        Song song = new Song("Smile", new Album("Smile", new Artist("Katy Perry")));
        listen.setSong(song);
        listen.setUser(userService.save(new User("Slava", "Slava", "v.krukovskyy@gmail.com", "", "", "M", new Date(1978 - 11 - 13),  new Country("Ukraine", "UA"))).getObject());
        listen.setListenDate(new Date());
        listen.setSyncId(12L);

        Listen result = listenService.addListen(listen).getObject();
        assertNotNull(listenService.getListen(result.getId()));
        Response<Listen> deletedListen = listenService.deleteById(9L);
        assertNull(listenService.getListen(9L));
        assertTrue(deletedListen.isOkay());
    }

    @Test
    public void deleteNotExistingListen() {
        Response<Listen> deletedListen = listenService.deleteById(999L);
        assertFalse(deletedListen.isOkay());
        assertNull(deletedListen.getObject());
    }

}