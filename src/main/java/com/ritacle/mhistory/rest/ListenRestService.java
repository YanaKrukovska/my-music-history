package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listen")
public class ListenRestService {

    @Autowired
    ListenService service;

    @GetMapping("/{id}")
    public Listen getListen(@PathVariable Long id) {

        return service.getListen(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Listen addListen(@RequestBody Listen listen){
        return service.addListen(listen);
    }

}
