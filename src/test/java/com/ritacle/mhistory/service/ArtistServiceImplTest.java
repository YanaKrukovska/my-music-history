package com.ritacle.mhistory.service;

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
public class ArtistServiceImplTest {

    @Autowired
    ArtistService service;

    @Test
    public void saveNullArtist() {
        Response<Artist> savedArtist = service.save(null);
        Assert.assertEquals(1, savedArtist.getErrors().size());
    }

    @Test
    public void saveArtistWithEmptyName() {
        Response<Artist> savedArtist = service.save(new Artist("      "));
        Assert.assertEquals(1, savedArtist.getErrors().size());
    }

    @Test
    public void saveNewArtist() {
        Artist artist = new Artist("Justin Bieber");
        Artist savedArtist = service.save(artist).getObject();
        Assert.assertNotNull(savedArtist.getId());
    }

    @Test
    public void saveExistingArtist() {
        Artist artist = new Artist("Ava Max");
        Response<Artist> savedArtist = service.save(artist);
        Assert.assertEquals(Long.valueOf(1), savedArtist.getObject().getId());
        Assert.assertEquals(0, savedArtist.getErrors().size());
    }
}