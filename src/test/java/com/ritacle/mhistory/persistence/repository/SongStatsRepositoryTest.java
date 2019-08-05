package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.stats.SongStats;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class SongStatsRepositoryTest {

    @Autowired
    SongStatsRepository repository;


    @Test
    public void getSongStats() {
        List<SongStats> result = repository.findFirst30ByUserMailIgnoreCase("jana.krua@gmail.com",
                Sort.by(Sort.Order.desc("listenDate")));
        Assert.assertTrue(result.size() > 0);
    }
}