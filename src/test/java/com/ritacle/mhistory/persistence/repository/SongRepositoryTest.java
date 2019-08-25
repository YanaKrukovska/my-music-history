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
        Album album = new Album();
        album.setId(3L);
        Assert.assertNotNull(repository.findSongByTitleIgnoreCaseAndAlbum("So Am I",  album));

    }

    @Test
    public void songNotFound() {
        Artist artist = new Artist(1L,"");
        Album album = new Album();
        album.setId(3L);
        Assert.assertNull(repository.findSongByTitleIgnoreCaseAndAlbum("So",  album));

    }

    @Test
    public void saveNewSong() {
        Song song = new Song("Pompeii",  new Album("Bad Blood", new Artist("Bastille")));
        Song result = repository.save(song);
        Assert.assertEquals(Long.valueOf(4), result.getId());
    }

}