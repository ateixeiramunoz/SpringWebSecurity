package com.eoi.springwebsecurity.filemanagement.services;

import com.eoi.springwebsecurity.filemanagement.models.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interfaz que define los métodos para la gestión de archivos.
 */
public interface FileSystemStorageService {

    /**
     * Inicializa el servicio de almacenamiento.
     *
     * @throws RuntimeException si se produce un error al inicializar el servicio.
     */
    void init();

    /**
     * Guarda el archivo proporcionado en el almacenamiento.
     *
     * @param file El archivo a guardar.
     * @throws RuntimeException si se produce un error al guardar el archivo.
     */
    void save(MultipartFile file);

    void saveUserFile(MultipartFile file, Long userID);

    /**
     * Carga el archivo con el nombre de archivo proporcionado desde el almacenamiento.
     *
     * @param filename El nombre del archivo a cargar.
     * @return Un objeto Resource que representa el archivo cargado.
     * @throws RuntimeException si el archivo no existe o no se puede leer.
     */
    Resource load(String filename);

    /**
     * Elimina todos los archivos del almacenamiento.
     */
    void deleteAll();

    /**
     * Carga todos los archivos del almacenamiento.
     *
     * @return Un Stream de objetos Path que representan los archivos cargados.
     * @throws RuntimeException si se produce un error al cargar los archivos.
     */
    List<FileInfo> loadAll();




    Resource loadAsResource(String filename);


}



