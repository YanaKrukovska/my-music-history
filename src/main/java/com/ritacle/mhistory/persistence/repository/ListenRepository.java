package com.ritacle.mhistory.persistence.repository;

import com.ritacle.mhistory.persistence.model.Listen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListenRepository  extends JpaRepository<Listen,Long> {

    Listen findByUserIdAndSyncId(Long listenerId, Long syncId);

    void deleteById(Long id);

    Listen findListenById(Long id);
}
