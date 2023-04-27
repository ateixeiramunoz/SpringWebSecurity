package com.eoi.springwebsecurity.filemanagement.services;

import com.eoi.springwebsecurity.filemanagement.entities.FileDB;
import com.eoi.springwebsecurity.filemanagement.models.FileInfo;
import com.eoi.springwebsecurity.filemanagement.repositories.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Db file storage service.
 */
@Service
public class DBFileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    /**
     * Store file db.
     *
     * @param file the file
     * @return the file db
     */
    public FileDB store(MultipartFile file)  {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = null;
        try {
            fileDB = new FileDB(null,fileName, file.getContentType(), file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fileDB = fileDBRepository.save(fileDB);

        return fileDB;
    }

    /**
     * Gets file.
     *
     * @param id the id
     * @return the file
     */
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    /**
     * Gets all files.
     *
     * @return the all files
     */
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    /**
     * Gets all file infos.
     *
     * @return the all file infos
     */
    public List<FileInfo> getAllFileInfos() {
        return fileDBRepository.findAll().stream().map(file -> {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(file.getFileName());
            fileInfo.setUrl("/files/" + file.getId());
            fileInfo.setType(file.getType());
            fileInfo.setSize(file.getData().length);
            return fileInfo;
        }).collect(Collectors.toList());
    }


    /**
     * Delete file.
     *
     * @param id the id
     */
    public void deleteFile(String id) {

        Optional<FileDB> file = fileDBRepository.findById(id);
        file.ifPresent(fileDB -> fileDBRepository.delete(fileDB));

    }

}
