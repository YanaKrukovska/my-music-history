package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import com.ritacle.mhistory.persistence.model.stats.TopAlbum;
import com.ritacle.mhistory.persistence.model.stats.TopArtist;
import com.ritacle.mhistory.persistence.model.stats.TopSong;
import com.ritacle.mhistory.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportRestService {

    private final StatisticsService statisticsService;
    Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    public ReportRestService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }


    @GetMapping("/listen/last/{mail}")
    public List<LastListen> getLastListens(@PathVariable String mail) {
        logger.debug("Getting last listens for user:{}", mail);
        return statisticsService.getLastUserListens(mail, Sort.by(Sort.Order.desc("listenDate")));
    }


    @RequestMapping(method = RequestMethod.GET, value = "/top/songs/{mail}/{startDate}/{endDate}")
    @ResponseBody
    public List<TopSong> getUserTopListens(@PathVariable String mail, @PathVariable
    @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                           @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        logger.debug("Getting top listens user:{} [{} - {})", mail, startDate, endDate);
        return statisticsService.getUserTopListens(mail, startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/top/albums/{mail}/{startDate}/{endDate}")
    @ResponseBody
    public List<TopAlbum> getUserTopAlbums(@PathVariable String mail, @PathVariable
    @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                           @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        logger.debug("Getting top albums user:{} [{} - {})", mail, startDate, endDate);
        return statisticsService.getUserTopAlbums(mail, startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/top/artists/{mail}/{startDate}/{endDate}")
    @ResponseBody
    public List<TopArtist> getUserTopArtists(@PathVariable String mail, @PathVariable
    @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                           @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        logger.debug("Getting top artists user:{} [{} - {})", mail, startDate, endDate);
        return statisticsService.getUserTopArtists(mail, startDate, endDate);
    }
}
