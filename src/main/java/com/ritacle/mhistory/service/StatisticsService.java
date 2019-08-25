package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.stats.LastListen;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StatisticsService {



  List<LastListen> getLastUserListens(String userMail, Sort sort);
}
