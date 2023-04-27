package com.eoi.springwebsecurity.filemanagement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class FileInfo {

    private String fileName;
    private String url;
    private String type;
    private long size;

}
