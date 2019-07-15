package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.persistence.repository.ListenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListenServiceImpl implements ListenService{

    @Autowired
    ListenRepository repository;

    @Override
    public Listen addListen(Listen listen) {
        return repository.save(listen);
    }

    @Override
    public Listen getListen(Long id) {
        return repository.getOne(id);
    }
}
