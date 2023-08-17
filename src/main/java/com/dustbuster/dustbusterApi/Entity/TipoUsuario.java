package com.dustbuster.dustbusterApi.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "TipoUsuario")
public class TipoUsuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_id")
    private Long tipoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
