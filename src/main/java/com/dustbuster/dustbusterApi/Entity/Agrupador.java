package com.dustbuster.dustbusterApi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Agrupadores")
public class Agrupador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agrupador_id")
    private Long agrupadorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Usuario usuario;

    @Column(name = "mi_descripcion")
    private String miDescripcion;


    private String habilidades;

    @Column(name = "numero_grupo")
    private String numeroGrupo;


    public Agrupador() {

    }
}
