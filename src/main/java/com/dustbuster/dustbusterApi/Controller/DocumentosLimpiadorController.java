package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.DocumentosLimpiador;
import com.dustbuster.dustbusterApi.Repository.IDocumentosLimpiadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentosLimpiadorController {

    @Autowired
    private IDocumentosLimpiadorRepository documentosRepository;

    @Autowired
    public DocumentosLimpiadorController(IDocumentosLimpiadorRepository documentosRepository) {
        this.documentosRepository = documentosRepository;
    }

    @GetMapping
    public ResponseEntity<List<DocumentosLimpiador>> obtenerTodosLosDocumentos() {
        List<DocumentosLimpiador> todosLosDocumentos = documentosRepository.findAll();
        if (todosLosDocumentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todosLosDocumentos, HttpStatus.OK);
    }

    // Crear un nuevo documento
    @PostMapping
    public ResponseEntity<DocumentosLimpiador> agregarDocumento(@RequestBody DocumentosLimpiador documento) {
        DocumentosLimpiador nuevoDocumento = documentosRepository.save(documento);
        return new ResponseEntity<>(nuevoDocumento, HttpStatus.CREATED);
    }

    // Obtener documentos por ID de usuario
    @GetMapping("/por-usuario/{userId}")
    public ResponseEntity<List<DocumentosLimpiador>> obtenerDocumentosPorUsuario(@PathVariable Long userId) {
        List<DocumentosLimpiador> documentosPorUsuario = documentosRepository.findByUserId(userId);
        if (documentosPorUsuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documentosPorUsuario, HttpStatus.OK);
    }
}

