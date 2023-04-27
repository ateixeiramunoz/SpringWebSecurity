package com.eoi.springwebsecurity.filemanagement.controllers;

import com.eoi.springwebsecurity.filemanagement.entities.FileDB;
import com.eoi.springwebsecurity.filemanagement.models.ResponseMessage;
import com.eoi.springwebsecurity.filemanagement.services.DBFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * The type Database file upload controller.
 */
@Controller
@RequestMapping("/database")
@CrossOrigin("http://localhost:8081")
public class DatabaseFileUploadController {


    @Autowired
    private DBFileStorageService storageService;

    /**
     * Upload file response entity.
     *
     * @param file the file
     * @return the response entity
     */
    @PostMapping("/files")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * List uploaded files string.
     *
     * @param model the model
     * @return the string
     * @throws IOException the io exception
     */
    @GetMapping("/files")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("DBfiles", storageService.getAllFiles().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileController.class,
                                "serveFile", path.getFileName()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "uploadForm";
    }


    /**
     * Gets file.
     *
     * @param id the id
     * @return the file
     */
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
                .body(fileDB.getData());
    }
}
