package com.ritacle.mhistory.persistence.repository;

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
        Assert.assertNotNull(repository.findSongByTitleIgnoreCaseAndArtistIgnoreCaseAndAlbumIgnoreCase("So Am I", "Ava Max", "So Am I"));

    }

    @Test
    public void songNotFound() {
        Assert.assertNull(repository.findSongByTitleIgnoreCaseAndArtistIgnoreCaseAndAlbumIgnoreCase("So", "Ava Max", "So Am I"));

    }

    @Test
    public void saveNewSong() {
        Song song = new Song("Pompeii", "Bastille", "Bad Blood");
        Song result = repository.save(song);
        Assert.assertEquals(Long.valueOf(4), result.getId());
    }

}