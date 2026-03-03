package com.crediapp.auth.controller;

import com.crediapp.auth.dto.BonoRequestDTO;
import com.crediapp.auth.service.BonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vivienda")
@CrossOrigin(origins = "*")
public class BonoController {

    @Autowired
    private BonoService bonoService;

    @PostMapping("/calcular-bbp")
    public ResponseEntity<?> calcularBono(@RequestBody BonoRequestDTO request) {
        try {

            Double montoBono = bonoService.calcularMontoBono(request);


            return ResponseEntity.ok(new BonoResponse(montoBono));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}


record BonoResponse(Double montoBono) {}
