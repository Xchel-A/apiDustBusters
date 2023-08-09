package com.dustbuster.dustbusterApi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name="DocumentosLimpiador")
public class DocumentosLimpiador {
/**
 * NIVELES USUARIO
 * ADMIN = 1
 * LIMPIADOR = 2
 * AGRUPADOR = 3
 * CLIENTE = 4
 *
 * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long DocId;


    @NotEmpty
    @Size(max = 100)
    @Column(name = "nombre_doc")
    private String nombreDoc;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "url_doc")
    private String urlDoc;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    public DocumentosLimpiador() {

    }
}
