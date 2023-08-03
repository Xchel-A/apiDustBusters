package com.dustbuster.dustbusterApi.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Limpiadores")
public class Limpiador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "limpiador_id")
    private Long limpiadorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")

    private Usuario usuario;

    @Column(name = "mi_descripcion")
    private String miDescripcion;

    private String habilidades;


    public Limpiador() {

    }
}
