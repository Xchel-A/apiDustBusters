package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.Cliente;
import com.dustbuster.dustbusterApi.Repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {



    @Autowired
    private IClienteRepository clienteRepository;

    // Get all Clientes
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Get Cliente by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new Cliente
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente newCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
    }

    // Update an existing Cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente updatedCliente) {
        Optional<Cliente> existingClienteOptional = clienteRepository.findById(id);
        if (existingClienteOptional.isPresent()) {
            Cliente existingCliente = existingClienteOptional.get();
            existingCliente.setMiDescripcion(updatedCliente.getMiDescripcion());
            existingCliente.setPreferencias(updatedCliente.getPreferencias());
            Cliente savedCliente = clienteRepository.save(existingCliente);
            return ResponseEntity.ok(savedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
