package com.dustbuster.dustbusterApi.Entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
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
@Table(name="Usuarios")
public class Usuario {
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
    @Column(name = "user_id")
    private Long UserId;

    @Column(name = "user_idOpenpay")
    private String UserIdOpenpay;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Size(max = 15)
    @Column(name = "numero_telefono")
    private String numeroTelefono;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "correo_electronico", unique = true)
    private String correo;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @Column(columnDefinition = "BIT(1)")
    private Boolean enabled;

    @Size(max = 200)
    private String curp;

    @NotNull
    @Column(name = "tipo_usuario")
    private int tipoUsuario;

    @Size(max = 200)
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "fecha_registro", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaRegistro;

    @Column(name = "ultima_sesion")
    private Timestamp ultimaSesion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Rol> authorities;

    public Usuario() {

    }
}
