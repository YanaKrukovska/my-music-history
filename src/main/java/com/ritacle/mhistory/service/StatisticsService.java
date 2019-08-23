package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.LastListen;
import com.ritacle.mhistory.persistence.model.ListenAmount;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public interface StatisticsService {

    List<LastListen> getLastUserListens(String userMail, Sort sort);

    List<ListenAmount> getUserTopListens(String userMail, Date startDate, Date endDate);


}
