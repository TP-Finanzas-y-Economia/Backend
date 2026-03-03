package com.crediapp.auth.controller;

import com.crediapp.auth.dto.BonoRequestDTO;
import com.crediapp.auth.service.BonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vivienda")
@CrossOrigin(origins = "*") // Para que tu frontend pueda conectarse sin problemas
public class BonoController {

    @Autowired
    private BonoService bonoService;

    @PostMapping("/calcular-bbp")
    public ResponseEntity<?> calcularBono(@RequestBody BonoRequestDTO request) {
        try {
            // Llamamos al servicio con la lógica de las preguntas (Sostenible, Integrador, etc.)
            Double montoBono = bonoService.calcularMontoBono(request);

            // Devolvemos el monto al usuario
            return ResponseEntity.ok(new BonoResponse(montoBono));
        } catch (Exception e) {
            // En caso de que el precio no esté en los rangos de la imagen (R1 a R5)
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}

// Clase de apoyo para devolver una respuesta limpia en JSON
record BonoResponse(Double montoBono) {}
