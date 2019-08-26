package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import com.ritacle.mhistory.persistence.model.stats.TopSong;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.ritacle.mhistory.utils.DateUtils.createDate;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class StatisticsServiceImplTest {
    @Autowired
    StatisticsService service;

    @Test
    public void getLastUserListens() {
        List<LastListen> result = service.getLastUserListens("v.krukovskyy@gmail.com", Sort.by(Sort.Order.desc("listenDate")));
        Assert.assertEquals(20, result.size());
    }


    @Test
    @Ignore
    public void getMonthUserSongs() {
        List<TopSong> result = service.getUserTopListens("v.krukovskyy@gmail.com", createDate(2019, 1, 1),
                createDate(2019, 1, 31));
        Assert.assertEquals(3, result.size());

    }
}