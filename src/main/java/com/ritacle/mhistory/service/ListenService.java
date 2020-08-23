package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.persistence.model.Response;

public interface ListenService {

    Response<Listen> addListen(Listen listen);

    Listen getListen(Long id);

    boolean checkIfExists(Long listenerId, Long syncId);

    Response<Listen> deleteById(Long id);

    Response<Listen> editListen(Listen listen);

}
