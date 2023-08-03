package com.dustbuster.dustbusterApi.Repository;

import com.dustbuster.dustbusterApi.Entity.Limpiador;
import com.dustbuster.dustbusterApi.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILimpiadorRepository extends JpaRepository<Limpiador, Long> {
}
