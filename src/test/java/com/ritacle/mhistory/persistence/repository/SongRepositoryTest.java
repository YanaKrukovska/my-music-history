package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.Song;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class SongRepositoryTest {

    @Autowired
    private SongRepository repository;

    @Test
    public void findSuccessfully() {
        Artist artist = new Artist(1L,"");
        Album album = new Album(3L, "");
        Assert.assertNotNull(repository.findSongByTitleIgnoreCaseAndArtistAndAlbum("So Am I", artist, album));

    }

    @Test
    public void songNotFound() {
        Artist artist = new Artist(1L,"");
        Album album = new Album(3L, "");
        Assert.assertNull(repository.findSongByTitleIgnoreCaseAndArtistAndAlbum("So", artist, album));

    }

    @Test
    public void saveNewSong() {
        Song song = new Song("Pompeii", new Artist("Bastille"), new Album("Bad Blood"));
        Song result = repository.save(song);
        Assert.assertEquals(Long.valueOf(4), result.getId());
    }

}