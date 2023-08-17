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
    public List<Servicio> obtenerServiciosEstado0() {
        List<Servicio> servicios = servicioRepository.findByEstado(0);
        return servicios;
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
        servicioExistente.setUrlImagenServicio(servicioActualizado.getUrlImagenServicio());
        servicioExistente.setDireccion(servicioActualizado.getDireccion());
        servicioExistente.setLatitud(servicioActualizado.getLatitud());
        servicioExistente.setLongitud(servicioActualizado.getLongitud());

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
    public ResponseEntity<List<Servicio>> obtenerServiciosPorIdClienteEstado0(@PathVariable Long id) {
        List<Servicio> servicios = servicioRepository.findByClienteIdAndEstado0(id);
        if (servicios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicios);
    }

    // Endpoint para obtener el historial de servicios (estado 1 o 2)
    @GetMapping("/historial/{id}")
    public ResponseEntity<List<Servicio>> obtenerHistorialServicios(@PathVariable Long id) {
        List<Servicio> historialServicios = servicioRepository.findHistorialByClienteId(id);
        if (historialServicios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historialServicios);
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