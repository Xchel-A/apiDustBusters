package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.Calificacion;
import com.dustbuster.dustbusterApi.Repository.ICalificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/calificaciones")
public class CalificacionController {

    @Autowired
    private ICalificacionesRepository calificacionRepository;

    @PostMapping
    public ResponseEntity<Calificacion> agregarCalificacion(@RequestBody Calificacion calificacion) {
        Calificacion nuevaCalificacion = calificacionRepository.save(calificacion);
        return new ResponseEntity<>(nuevaCalificacion, HttpStatus.CREATED);
    }

    @GetMapping("/por-usuario/{idUsuario}")
    public ResponseEntity<List<Calificacion>> obtenerCalificacionesPorUsuario(@PathVariable Long idUsuario) {
        List<Calificacion> calificacionesPorUsuario = calificacionRepository.findByIdUsuarioCalificado(idUsuario);
        return new ResponseEntity<>(calificacionesPorUsuario, HttpStatus.OK);
    }

    @GetMapping("/calificacion-general/{idUsuario}")
    public ResponseEntity<Double> obtenerCalificacionGeneralUsuario(@PathVariable Long idUsuario) {
        List<Calificacion> calificacionesPorUsuario = calificacionRepository.findByIdUsuarioCalificado(idUsuario);

        if (calificacionesPorUsuario.isEmpty()) {
            return new ResponseEntity<>(0.0, HttpStatus.OK);
        }

        double sumaCalificaciones = calificacionesPorUsuario.stream()
                .mapToInt(Calificacion::getCalificacion)
                .sum();

        double calificacionGeneral = sumaCalificaciones / calificacionesPorUsuario.size();

        return new ResponseEntity<>(calificacionGeneral, HttpStatus.OK);
    }
}
