package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import com.ritacle.mhistory.persistence.model.stats.TopSong;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public interface StatisticsService {

    List<LastListen> getLastUserListens(String userMail, Sort sort);

    List<TopSong> getUserTopListens(String userMail, Date startDate, Date endDate);


}
