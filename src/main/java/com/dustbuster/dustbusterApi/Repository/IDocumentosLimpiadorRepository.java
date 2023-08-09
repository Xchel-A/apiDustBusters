package com.dustbuster.dustbusterApi.Repository;

import com.dustbuster.dustbusterApi.Entity.DocumentosLimpiador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDocumentosLimpiadorRepository extends JpaRepository<DocumentosLimpiador, Long> {

    // Método para obtener los documentos asociados a un usuario específico
    @Query("SELECT d FROM DocumentosLimpiador d WHERE d.userId = :userId")
    List<DocumentosLimpiador> findByUserId(Long userId);
}
