package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.Usuario;
import com.dustbuster.dustbusterApi.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    // Obtener todos los usuarios
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por su ID
    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    // Crear un nuevo usuario
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        // Antes de guardar, hasheamos la contraseña usando el encoder
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente
    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
            usuario.setNumeroTelefono(usuarioActualizado.getNumeroTelefono());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setEnabled(usuarioActualizado.getEnabled());
            usuario.setTipoUsuario(usuarioActualizado.getTipoUsuario());
            usuario.setFotoPerfil(usuarioActualizado.getFotoPerfil());
            usuario.setFechaRegistro(usuarioActualizado.getFechaRegistro());
            usuario.setUltimaSesion(usuarioActualizado.getUltimaSesion());
            usuario.setAuthorities(usuarioActualizado.getAuthorities());
            // Actualizar otros campos según corresponda
            return usuarioRepository.save(usuario);
        }
        return null; // O puedes lanzar una excepción si no se encuentra el usuario
    }


    // Eliminar un usuario por su ID
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
