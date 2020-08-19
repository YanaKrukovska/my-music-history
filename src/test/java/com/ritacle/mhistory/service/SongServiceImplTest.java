package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.Response;
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
    public void saveNullSong() {
        Response<Song> savedSong = service.save(null);
        Assert.assertEquals(1, savedSong.getErrors().size());
    }

    @Test
    public void saveSongWithNullTitle() {
        Song song = new Song(null, new Album("13 Voices", new Artist("Sum 41")));
        Response<Song> savedSong = service.save(song);
        Assert.assertEquals(1, savedSong.getErrors().size());
    }

    @Test
    public void saveSongWithNullArtist() {
        Song song = new Song("Ashes", new Album("Deadpool Soundtrack", null));
        Response<Song> savedSong = service.save(song);
        Assert.assertEquals(1, savedSong.getErrors().size());
    }

    @Test
    public void saveSongWithEmptyArtistName() {
        Song song = new Song("Ashes", new Album("Deadpool Soundtrack", new Artist("     ")));
        Response<Song> savedSong = service.save(song);
        Assert.assertEquals(1, savedSong.getErrors().size());
    }

    @Test
    public void saveSongWithNullAlbum() {
        Song song = new Song("Love Me Like You Do", null);
        Response<Song> savedSong = service.save(song);
        Assert.assertEquals(1, savedSong.getErrors().size());
    }

    @Test
    public void saveSongWithEmptyAlbumName() {
        Song song = new Song("The A Team", new Album("", new Artist("Ed Sheeran")));
        Response<Song> savedSong = service.save(song);
        Assert.assertEquals(1, savedSong.getErrors().size());
    }

    @Test
    public void saveNewSong() {
        Song song = new Song("My Way", new Album("My Way", new Artist("Ava Max")));
        Song savedSong = service.save(song).getObject();
        Assert.assertNotNull(savedSong.getId());
    }

    @Test
    public void saveExistingSong() {
        Song song = new Song("Grow Old With Me", new Album("Long Way Down", new Artist("Tom Odell")));
        Song savedSong = service.save(song).getObject();
        Assert.assertNotNull(savedSong.getId());
    }

}