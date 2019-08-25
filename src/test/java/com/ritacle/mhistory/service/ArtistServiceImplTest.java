package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Artist;
import com.ritacle.mhistory.persistence.repository.ArtistRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class ArtistServiceImplTest {

    @Autowired
    ArtistService service;


    @Test
    public void save() {
        Artist artist = new Artist("Justin Bieber");
        Artist savedArtist = service.save(artist);
        Assert.assertNotNull(savedArtist.getId());
    }


}