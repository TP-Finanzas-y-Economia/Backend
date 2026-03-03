package com.crediapp.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crediapp.auth.dto.CronogramaResponseDTO;
import com.crediapp.auth.dto.SimulacionRequestDTO;
import com.crediapp.auth.entity.EntidadFinanciera;
import com.crediapp.auth.repository.EntidadFinancieraRepository;
import com.crediapp.auth.service.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cronograma")
@CrossOrigin(origins = "*")
public class CronogramaController {

    @Autowired
    private EntidadFinancieraRepository entidadRepo;

    @Autowired
    private CronogramaService cronogramaService;


    @GetMapping("/bancos-disponibles")
    public ResponseEntity<List<EntidadFinanciera>> obtenerBancos(
            @RequestParam Double sueldo,
            @RequestParam Double precio) {

        List<EntidadFinanciera> bancos = entidadRepo.findBancosDisponibles(sueldo, precio);
        return ResponseEntity.ok(bancos);
    }


    @PostMapping("/generar-cronograma")
    public ResponseEntity<?> generar(@RequestBody SimulacionRequestDTO request) {
        try {
            CronogramaResponseDTO response = cronogramaService.generarCronograma(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en el cálculo: " + e.getMessage());
        }
    }
}