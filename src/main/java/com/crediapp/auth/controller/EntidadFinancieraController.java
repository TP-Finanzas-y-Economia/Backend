package com.crediapp.auth.controller;
import com.crediapp.auth.entity.EntidadFinanciera;
import com.crediapp.auth.service.EntidadFinancieraService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/entidades")
@SecurityRequirement(name = "bearerAuth")

public class EntidadFinancieraController {
    @Autowired
    private EntidadFinancieraService service;


    @PostMapping
    public ResponseEntity<EntidadFinanciera> crear(@RequestBody EntidadFinanciera entidad) {
        return new ResponseEntity<>(service.createEntidad(entidad), HttpStatus.CREATED);
    }


    @GetMapping
    public List<EntidadFinanciera> listar() {
        return service.getAllEntidades();
    }

    // 3. LEER un banco específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<EntidadFinanciera> buscarPorId(@PathVariable Integer id) {
        return service.getEntidadById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<EntidadFinanciera> actualizar(@PathVariable Integer id, @RequestBody EntidadFinanciera details) {
        try {
            return ResponseEntity.ok(service.updateEntidad(id, details));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.deleteEntidad(id);
        return ResponseEntity.noContent().build();
    }
}
