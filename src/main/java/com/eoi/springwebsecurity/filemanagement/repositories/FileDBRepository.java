package com.eoi.springwebsecurity.filemanagement.repositories;

import com.eoi.springwebsecurity.filemanagement.entities.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {



}
