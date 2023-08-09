package com.dustbuster.dustbusterApi.Repository;


import com.dustbuster.dustbusterApi.Entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICalificacionesRepository extends JpaRepository<Calificacion, Long> {

    List<Calificacion> findByIdUsuarioCalificado(Long idUsuarioCalificado);

}
