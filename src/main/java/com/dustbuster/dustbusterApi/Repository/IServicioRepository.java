package com.dustbuster.dustbusterApi.Repository;

import com.dustbuster.dustbusterApi.Entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IServicioRepository extends JpaRepository<Servicio, Long> {

    @Query("SELECT s FROM Servicio s WHERE s.limpiador.UserId = :limpiadorId")
    List<Servicio> findByLimpiadorId(Long limpiadorId);

    @Query("SELECT s FROM Servicio s WHERE s.cliente.UserId = :clienteId")
    List<Servicio> findByClienteId(Long clienteId);

}
