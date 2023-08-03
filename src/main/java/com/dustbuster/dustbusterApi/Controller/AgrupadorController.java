package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.Agrupador;
import com.dustbuster.dustbusterApi.Entity.Limpiador;
import com.dustbuster.dustbusterApi.Repository.IAgrupadorRepository;
import com.dustbuster.dustbusterApi.Repository.ILimpiadorRepository;
import com.dustbuster.dustbusterApi.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/agrupador")
public class AgrupadorController {

    @Autowired
    private IAgrupadorRepository agrupadorRepository;

    // Get all Agrupadores
    @GetMapping
    public List<Agrupador> getAllAgrupadores() {
        return agrupadorRepository.findAll();
    }

    // Get Agrupador by ID
    @GetMapping("/{id}")
    public ResponseEntity<Agrupador> getAgrupadorById(@PathVariable Long id) {
        Optional<Agrupador> agrupador = agrupadorRepository.findById(id);
        return agrupador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new Agrupador
    @PostMapping
    public ResponseEntity<Agrupador> createAgrupador(@RequestBody Agrupador agrupador) {
        Agrupador newAgrupador = agrupadorRepository.save(agrupador);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAgrupador);
    }

    // Update an existing Agrupador
    @PutMapping("/{id}")
    public ResponseEntity<Agrupador> updateAgrupador(@PathVariable Long id, @RequestBody Agrupador updatedAgrupador) {
        Optional<Agrupador> existingAgrupadorOptional = agrupadorRepository.findById(id);
        if (existingAgrupadorOptional.isPresent()) {
            Agrupador existingAgrupador = existingAgrupadorOptional.get();
            existingAgrupador.setMiDescripcion(updatedAgrupador.getMiDescripcion());
            existingAgrupador.setHabilidades(updatedAgrupador.getHabilidades());
            existingAgrupador.setNumeroGrupo(updatedAgrupador.getNumeroGrupo());

            Agrupador savedAgrupador = agrupadorRepository.save(existingAgrupador);
            return ResponseEntity.ok(savedAgrupador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an Agrupador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgrupador(@PathVariable Long id) {
        agrupadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
