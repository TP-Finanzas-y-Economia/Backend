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
    public ResponseEntity<?> obtenerBancos(
            @RequestParam(required = true) Double sueldo,
            @RequestParam(required = true) Double precio,
            @RequestParam(required = true) Double cuotaInicial) {

        // Log para depuración en consola de IntelliJ
        System.out.println("Buscando bancos para Sueldo: " + sueldo + ", Precio: " + precio + ", Inicial: " + cuotaInicial);

        List<EntidadFinanciera> bancos = entidadRepo.findBancosDisponibles(sueldo, precio, cuotaInicial);

        if (bancos.isEmpty()) {
            return ResponseEntity.ok("No se encontraron bancos que coincidan con los criterios.");
        }

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