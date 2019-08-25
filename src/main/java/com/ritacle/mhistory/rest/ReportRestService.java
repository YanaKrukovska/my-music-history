package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import com.ritacle.mhistory.persistence.model.stats.TopSong;
import com.ritacle.mhistory.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportRestService {

    @Autowired
    private StatisticsService statisticsService;
    Logger logger = LoggerFactory.getLogger(StatisticsService.class);


    @GetMapping("/listen/last/{mail}")
    public List<LastListen> getLastListens(@PathVariable String mail) {
        logger.debug("Getting last listens for user:{} [{} - {})", mail);
        return statisticsService.getLastUserListens(mail, Sort.by(Sort.Order.desc("listenDate")));
    }

    @GetMapping("/top/songs/{mail}/{startDate}/{endDate}")
    @ResponseBody
    public List<TopSong> getUserTopListens(@PathVariable String mail, @PathVariable Date startDate, @PathVariable Date endDate) {
        logger.debug("Getting top listens user:{} [{} - {})", mail, startDate, endDate);
        return statisticsService.getUserTopListens(mail, startDate, endDate);
    }
}
