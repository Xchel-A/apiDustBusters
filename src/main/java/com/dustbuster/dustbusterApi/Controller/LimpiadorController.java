package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.Limpiador;
import com.dustbuster.dustbusterApi.Repository.ILimpiadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/limpiadores")
public class LimpiadorController {

    @Autowired
    private ILimpiadorRepository limpiadorRepository;

    // Obtener todos los limpiadores
    @GetMapping
    public List<Limpiador> obtenerTodosLosLimpiadores() {
        return limpiadorRepository.findAll();
    }

    // Obtener un limpiador por su ID
    @GetMapping("/{id}")
    public Optional<Limpiador> obtenerLimpiadorPorId(@PathVariable Long id) {
        return limpiadorRepository.findById(id);
    }

    // Crear un nuevo limpiador
    @PostMapping
    public Limpiador crearLimpiador(@RequestBody Limpiador limpiador) {
        return limpiadorRepository.save(limpiador);
    }

    // Actualizar un limpiador existente
    @PutMapping("/{id}")
    public Limpiador actualizarLimpiador(@PathVariable Long id, @RequestBody Limpiador limpiadorActualizado) {
        Optional<Limpiador> limpiadorExistente = limpiadorRepository.findById(id);
        if (limpiadorExistente.isPresent()) {
            Limpiador limpiador = limpiadorExistente.get();
            limpiador.setUsuario(limpiadorActualizado.getUsuario());
            limpiador.setMiDescripcion(limpiadorActualizado.getMiDescripcion());
            limpiador.setHabilidades(limpiadorActualizado.getHabilidades());


            // Actualizar otros campos según corresponda

            return limpiadorRepository.save(limpiador);
        }
        return null; // O puedes lanzar una excepción si no se encuentra el limpiador
    }

    // Eliminar un limpiador por su ID
    @DeleteMapping("/{id}")
    public void eliminarLimpiador(@PathVariable Long id) {
        limpiadorRepository.deleteById(id);
    }
}
