package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.model.Response;
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
public class AlbumServiceImplTest {

    @Autowired
    AlbumService service;

    @Test
    public void saveNullAlbum() {
        Response<Album> savedAlbum = service.save(null);
        Assert.assertEquals(1, savedAlbum.getErrors().size());
    }

    @Test
    public void saveAlbumWithNullArtist() {
        Album album = new Album("Cats Soundtrack", null);
        Response<Album> savedAlbum = service.save(album);
        Assert.assertEquals(1, savedAlbum.getErrors().size());
    }

    @Test
    public void saveAlbumWithEmptyTitle() {
        Album album = new Album("       ", new Artist("Celine Dion"));
        Response<Album> savedAlbum = service.save(album);
        Assert.assertEquals(1, savedAlbum.getErrors().size());
    }

    @Test
    public void saveAlbumWithIncorrectArtist() {
        Album album = new Album("Bleed American", new Artist("      "));
        Response<Album> savedAlbum = service.save(album);
        Assert.assertEquals(1, savedAlbum.getErrors().size());
    }

    @Test
    public void saveNewAlbum() {
        Album album = new Album("Solo", new Artist("Anitta"));
        Album savedAlbum = service.save(album).getObject();
        Assert.assertNotNull(savedAlbum.getId());
    }

    @Test
    public void saveExistingAlbum() {
        Album album = new Album("Wrong Crowd", new Artist("Tom Odell"));
        Response<Album> savedAlbum = service.save(album);
        Assert.assertEquals(Long.valueOf(4), savedAlbum.getObject().getId());
        Assert.assertEquals(0, savedAlbum.getErrors().size());
    }
}