package com.dustbuster.dustbusterApi.Repository;

import com.dustbuster.dustbusterApi.Entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IServicioRepository extends JpaRepository<Servicio, Long> {

    @Query("SELECT s FROM Servicio s WHERE s.limpiador.UserId = :limpiadorId")
    List<Servicio> findByLimpiadorId(Long limpiadorId);

    @Query("SELECT s FROM Servicio s WHERE s.cliente.UserId = :clienteId AND s.estado = 0")
    List<Servicio> findByClienteIdAndEstado0(Long clienteId);

    @Query("SELECT s FROM Servicio s WHERE s.cliente.UserId = :clienteId AND s.estado IN (1, 2)")
    List<Servicio> findHistorialByClienteId(Long clienteId);

    @Query("SELECT s FROM Servicio s WHERE s.estado = :estado")
    List<Servicio> findByEstado(int estado);
}
