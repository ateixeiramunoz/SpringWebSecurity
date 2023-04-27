package com.eoi.springwebsecurity.streaming.controller;

import com.eoi.springwebsecurity.filemanagement.services.FileSystemStorageService;
import com.eoi.springwebsecurity.streaming.services.StreamingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * The type Streaming controller.
 */
@RestController
@Log4j2
public class StreamingController {

    @Autowired
    private StreamingService streamingService;


    /**
     * Gets video.
     *
     * @param title the title
     * @param range the range
     * @return the video
     */
    @GetMapping(value = "/stream/{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader(name = "Range", defaultValue = "bytes=0-50") String range) {
        log.info(range);
        return streamingService.getVideo(title);
    }




}