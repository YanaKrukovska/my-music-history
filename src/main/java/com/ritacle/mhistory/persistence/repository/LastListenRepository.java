package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.LastListen;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LastListenRepository extends JpaRepository<LastListen, Long> {

    List<LastListen> findFirst30ByUserMailIgnoreCase(String userMail, Sort sort);
}
