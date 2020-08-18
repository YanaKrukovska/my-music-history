package com.ritacle.mhistory.rest;

import com.ritacle.mhistory.persistence.model.Listen;
import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/listen")
public class ListenRestService {
    private Logger logger = LoggerFactory.getLogger(ListenRestService.class);

    private ListenService service;

    @Autowired
    public ListenRestService(ListenService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Listen getListen(@PathVariable Long id) {
        logger.debug("Listen GET id={} ", id);
        return service.getListen(id);
    }

    @PostMapping
    @ResponseBody
    public Response<Listen> addListen(@RequestBody Listen listen) {
        logger.debug("Listen PUT :{}", listen.toString());
        return service.addListen(listen);
    }

    @GetMapping("/check/{listenerId}/{syncId}")
    public boolean checkIfExists(@PathVariable Long listenerId, @PathVariable Long syncId) {
        logger.debug("Listen check listenerId={} syncId={}", listenerId, syncId);
        return service.checkIfExists(listenerId, syncId);
    }


}
