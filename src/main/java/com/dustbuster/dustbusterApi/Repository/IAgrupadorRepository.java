package com.dustbuster.dustbusterApi.Repository;

import com.dustbuster.dustbusterApi.Entity.Agrupador;
import com.dustbuster.dustbusterApi.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgrupadorRepository extends JpaRepository<Agrupador, Long> {
}
