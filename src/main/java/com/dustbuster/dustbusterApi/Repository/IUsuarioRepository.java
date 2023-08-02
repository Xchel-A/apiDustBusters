package com.dustbuster.dustbusterApi.Repository;

import com.dustbuster.dustbusterApi.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario , Long> {
    Optional<Usuario> findByCorreo(String correo);
    //UsuarioDTO findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
}
