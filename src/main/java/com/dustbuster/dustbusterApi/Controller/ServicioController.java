package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.Servicio;
import com.dustbuster.dustbusterApi.Repository.IServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private IServicioRepository servicioRepository;

    // Endpoint para obtener todos los servicios
    @GetMapping
    public List<Servicio> obtenerTodosServicios() {
        return servicioRepository.findAll();
    }

    // Endpoint para obtener un servicio por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable Long id) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicio);
    }

    // Endpoint para crear un nuevo servicio
    @PostMapping
    public Servicio crearServicio(@RequestBody Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    // Endpoint para actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio servicioActualizado) {
        Servicio servicioExistente = servicioRepository.findById(id).orElse(null);
        if (servicioExistente == null) {
            return ResponseEntity.notFound().build();
        }
        // Actualizar los campos del servicio existente con los datos del servicio actualizado
        servicioExistente.setCliente(servicioActualizado.getCliente());
        servicioExistente.setLimpiador(servicioActualizado.getLimpiador());
        servicioExistente.setDescripcionServicio(servicioActualizado.getDescripcionServicio());
        servicioExistente.setTamanoInmueble(servicioActualizado.getTamanoInmueble());
        servicioExistente.setPlantas(servicioActualizado.getPlantas());
        servicioExistente.setOfertaCliente(servicioActualizado.getOfertaCliente());
        servicioExistente.setOfertaAceptada(servicioActualizado.getOfertaAceptada());
        servicioExistente.setFechaServicio(servicioActualizado.getFechaServicio());
        servicioExistente.setHoraInicio(servicioActualizado.getHoraInicio());
        servicioExistente.setHoraFin(servicioActualizado.getHoraFin());
        servicioExistente.setEstado(servicioActualizado.getEstado());

        servicioRepository.save(servicioExistente);
        return ResponseEntity.ok(servicioExistente);
    }

    // Endpoint para eliminar un servicio por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        servicioRepository.delete(servicio);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("byidcliente/{id}")
    public ResponseEntity<List<Servicio>> obtenerServicioPorIdCliente(@PathVariable Long id) {
        List<Servicio> servicios = servicioRepository.findByClienteId(id);
        if (servicios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("byidlimpiador/{id}")
    public ResponseEntity<List<Servicio>> obtenerServicioPorIdLimpiador(@PathVariable Long id) {
        List<Servicio> servicios = servicioRepository.findByLimpiadorId(id);
        if (servicios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicios);
    }
}
