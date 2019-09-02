package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Listen;

public interface ListenService {

    Listen addListen(Listen listen);

    Listen getListen(Long id);

    boolean checkIfExists(Long listenerId, Long syncId);

}
