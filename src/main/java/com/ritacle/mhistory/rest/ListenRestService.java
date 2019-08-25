package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/listen")
public class ListenRestService {
    Logger logger = LoggerFactory.getLogger(ListenRestService.class);

    @Autowired
    ListenService service;

    @GetMapping("/{id}")
    public Listen getListen(@PathVariable Long id) {
        logger.debug("Listen GET");
        return service.getListen(id);
    }

    @PostMapping
    @ResponseBody
    public Listen addListen(@RequestBody Listen listen) {
        logger.debug("Listen PUT");
        return service.addListen(listen);
    }

}
