package com.eoi.springwebsecurity.streaming.controller;

import com.eoi.springwebsecurity.filemanagement.models.FileInfo;
import com.eoi.springwebsecurity.filemanagement.services.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class VideoListController {

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @GetMapping("/videos")
    public String listAllVideos(Model model) {
        List<FileInfo> videoFiles = fileSystemStorageService.loadAllByFileType("video/mp4");
        model.addAttribute("videos", videoFiles);
        return "videoList";
    }



}
