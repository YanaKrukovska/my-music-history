package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Album;
import com.ritacle.mhistory.persistence.model.Artist;
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
    public void save() {
        Album album = new Album("Solo", new Artist("Anitta"));
        Album savedAlbum = service.save(album).getObject();
        Assert.assertNotNull(savedAlbum.getId());
    }
}