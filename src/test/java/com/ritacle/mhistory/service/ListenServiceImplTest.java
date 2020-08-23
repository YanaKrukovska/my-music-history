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
    public void addNullListen() {
        Response<Listen> result = listenService.addListen(null);
        assertEquals(1, result.getErrors().size());
    }

    @Test
    public void addListenWithNullSong() {
        Listen listen = new Listen();
        listen.setSong(null);
        Response<Listen> result = listenService.addListen(listen);
        assertEquals(1, result.getErrors().size());
    }

    @Test
    public void addListenWithNullArtist() {
        Listen listen = new Listen();
        Song song = new Song("Somebody Told Me", new Album("Hot Fuss", null));
        listen.setSong(song);
        Response<Listen> result = listenService.addListen(listen);
        assertEquals(1, result.getErrors().size());
    }

    @Test
    public void addListenWithNullAlbum() {
        Listen listen = new Listen();
        Song song = new Song("Wonderful Wonderful", null);
        listen.setSong(song);
        Response<Listen> result = listenService.addListen(listen);
        assertEquals(1, result.getErrors().size());
    }

    @Test
    public void addListenWithBlankParameters() {
        Listen listen = new Listen();
        Song song = new Song("        ", new Album(null, new Artist("")));
        listen.setSong(song);
        Response<Listen> result = listenService.addListen(listen);
        assertEquals(3, result.getErrors().size());
    }

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
        listen.setUser(new User("Slava", "Slava", "v.krukovskyy@gmail.com", "", "", "M", new Date(1978 - 11 - 13), new Country("Ukraine", "UA")));
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
        listen.setUser(userService.save(new User("Slava", "Slava", "v.krukovskyy@gmail.com", "", "", "M", new Date(1978 - 11 - 13), new Country("Ukraine", "UA"))).getObject());
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

    @Test
    public void editSongCorrectListen() {
        Listen listen = new Listen();
        Song song = new Song("So Am I (Acoustic)", new Album("So Am I", new Artist("Ava Max")));
        listen.setSong(song);
        listen.setId(3L);

        String dbListen = listenService.getListen(3L).getSong().getTitle();
        assertEquals("So Am I", dbListen);
        Response<Listen> editedListen = listenService.editListen(listen);
        assertTrue(editedListen.isOkay());

        dbListen = listenService.getListen(3L).getSong().getTitle();
        assertEquals("So Am I (Acoustic)", dbListen);
    }

    @Test
    public void editAlbumCorrectListen() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho", new Album("Heaven & Hell", new Artist("Ava Max")));
        listen.setSong(song);
        listen.setId(1L);

        String dbListen = listenService.getListen(1L).getSong().getAlbum().getTitle();
        assertEquals("Sweet But Psycho", dbListen);
        Response<Listen> editedListen = listenService.editListen(listen);
        assertTrue(editedListen.isOkay());

        dbListen = listenService.getListen(1L).getSong().getAlbum().getTitle();
        assertEquals("Heaven & Hell", dbListen);
    }

    @Test
    public void editArtistCorrectListen() {
        Listen listen = new Listen();
        Song song = new Song("I Know", new Album("Long Way Down", new Artist("Post Malone")));
        listen.setSong(song);
        listen.setId(7L);

        String dbListen = listenService.getListen(7L).getSong().getAlbum().getArtist().getName();
        assertEquals("Tom Odell", dbListen);
        Response<Listen> editedListen = listenService.editListen(listen);
        assertTrue(editedListen.isOkay());

        dbListen = listenService.getListen(7L).getSong().getAlbum().getArtist().getName();
        assertEquals("Post Malone", dbListen);
    }

    @Test
    public void editNullListen() {
        Response<Listen> updatedListen = listenService.editListen(null);
        assertEquals(1, updatedListen.getErrors().size());
    }

    @Test
    public void editListenNoSong() {
        Listen listen = new Listen();
        listen.setSong(null);
        Response<Listen> updatedListen = listenService.editListen(listen);
        assertEquals(1, updatedListen.getErrors().size());
    }

    @Test
    public void editListenNoAlbum() {
        Listen listen = new Listen();
        listen.setSong( new Song("Sweet But Psycho", null));
        Response<Listen> updatedListen = listenService.editListen(listen);
        assertEquals(1, updatedListen.getErrors().size());
    }

    @Test
    public void editListenNoArtist() {
        Listen listen = new Listen();
        listen.setSong( new Song("Sweet But Psycho",  new Album("Heaven & Hell", null)));
        Response<Listen> updatedListen = listenService.editListen(listen);
        assertEquals(1, updatedListen.getErrors().size());
    }

    @Test
    public void editListenIncorrectTitles() {
        Listen listen = new Listen();
        listen.setSong( new Song("     ",  new Album("", new Artist(null))));
        listen.setId(1L);
        Response<Listen> updatedListen = listenService.editListen(listen);
        assertEquals(3, updatedListen.getErrors().size());
    }

    @Test
    public void editNotExistingListen() {
        Listen listen = new Listen();
        Song song = new Song("Sweet But Psycho", new Album("Heaven & Hell", new Artist("Ava Max")));
        listen.setSong(song);
        listen.setId(700L);

        Response<Listen> updatedListen = listenService.editListen(listen);
        assertEquals(1, updatedListen.getErrors().size());
    }
}