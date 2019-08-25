package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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
        Assert.assertEquals(5, result.size());
    }
}