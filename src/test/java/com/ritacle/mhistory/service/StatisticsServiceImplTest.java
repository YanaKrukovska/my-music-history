package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import com.ritacle.mhistory.persistence.model.stats.TopAlbum;
import com.ritacle.mhistory.persistence.model.stats.TopArtist;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class StatisticsServiceImplTest {
    @Autowired
    StatisticsService service;

    @Test
    public void getLastUserListens() {
        List<LastListen> result = service.getLastUserListens("v.krukovskyy@gmail.com", Sort.by(Sort.Order.desc("listenDate")));
        Assert.assertEquals(7, result.size());
    }

    @Test
    public void getUserTopAlbums() {
        List<TopAlbum> result = service.getUserTopAlbums("v.krukovskyy@gmail.com", new Date(2, Calendar.JANUARY, 100), new Date());
        Assert.assertEquals(4, result.get(0).getListenCount());
    }

    @Test
    public void getUserTopArtists() {
        List<TopArtist> result = service.getUserTopArtists("v.krukovskyy@gmail.com", new Date(2, Calendar.JANUARY, 100), new Date());
        Assert.assertEquals(5, result.get(0).getListenCount());

    }
}