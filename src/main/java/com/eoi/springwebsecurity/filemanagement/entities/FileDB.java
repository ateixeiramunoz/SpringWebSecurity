package com.eoi.springwebsecurity.filemanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


/**
 * Clase que representa un archivo en la base de datos.
 */
@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDB {

    /**
     * Identificador único del archivo, generado automáticamente.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    /**
     * Nombre del archivo.
     */
    @NotEmpty
    private String fileName;

    /**
     * Tipo de archivo.
     */
    @NotEmpty
    private String type;

    /**
     * Datos del archivo almacenados como un array de bytes en la base de datos.
     */
    @Lob
    @NotEmpty
    @Column(name="data", nullable=false, columnDefinition="LONGBLOB")
    private byte[] data;

}
