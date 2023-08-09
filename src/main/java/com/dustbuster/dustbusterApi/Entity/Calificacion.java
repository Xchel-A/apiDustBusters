package com.dustbuster.dustbusterApi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calificacion_id")
    private Long calificacionId;

    @Column(name = "calificacion")
    private int calificacion;

    @Column(name = "nombre_calificador")
    private String nombreCalificador;

    @Column(name = "id_usuario_calificado")
    private Long idUsuarioCalificado;

    @Column(name = "url_imagen_calificador")
    private String urlImagenCalificador;

    @Column(name = "comentario")
    private String comentario;

    public Calificacion(int calificacion, String nombreCalificador, Long idUsuarioCalificado, String urlImagenCalificador, String comentario) {
        this.calificacion = calificacion;
        this.nombreCalificador = nombreCalificador;
        this.idUsuarioCalificado = idUsuarioCalificado;
        this.urlImagenCalificador = urlImagenCalificador;
        this.comentario = comentario;
    }

    public Calificacion() {

    }
}
