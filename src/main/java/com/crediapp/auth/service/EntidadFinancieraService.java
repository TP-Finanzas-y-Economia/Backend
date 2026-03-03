package com.crediapp.auth.service;
import com.crediapp.auth.entity.EntidadFinanciera;
import com.crediapp.auth.repository.EntidadFinancieraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EntidadFinancieraService {
    @Autowired
    private EntidadFinancieraRepository repository;


    public EntidadFinanciera createEntidad(EntidadFinanciera entidad) {
        return repository.save(entidad);
    }


    public List<EntidadFinanciera> getAllEntidades() {
        return repository.findAll();
    }


    public Optional<EntidadFinanciera> getEntidadById(Integer id) {
        return repository.findById(id);
    }


    public EntidadFinanciera updateEntidad(Integer id, EntidadFinanciera details) {
        return repository.findById(id).map(entidad -> {

            if (details.getNombreEntidad() != null) entidad.setNombreEntidad(details.getNombreEntidad());
            if (details.getTasaEfectivaAnual() != null) entidad.setTasaEfectivaAnual(details.getTasaEfectivaAnual());
            if (details.getTasaSeguroDesgravamen() != null) entidad.setTasaSeguroDesgravamen(details.getTasaSeguroDesgravamen());
            if (details.getTasaSeguroInmueble() != null) entidad.setTasaSeguroInmueble(details.getTasaSeguroInmueble());
            if (details.getIngresoMinimoRequerido() != null) entidad.setIngresoMinimoRequerido(details.getIngresoMinimoRequerido());
            if (details.getMontoMaximoPrestamo() != null) entidad.setMontoMaximoPrestamo(details.getMontoMaximoPrestamo());
            if (details.getPeriodoGraciaMaximo() != null) entidad.setPeriodoGraciaMaximo(details.getPeriodoGraciaMaximo());
            if (details.getPrecioMinVivienda() != null) entidad.setPrecioMinVivienda(details.getPrecioMinVivienda());
            if (details.getPrecioMaxVivienda() != null) entidad.setPrecioMaxVivienda(details.getPrecioMaxVivienda());
            if (details.getAdmiteCRC() != null) entidad.setAdmiteCRC(details.getAdmiteCRC());

            return repository.save(entidad);
        }).orElseThrow(() -> new RuntimeException("Entidad financiera no encontrada"));
    }


    public void deleteEntidad(Integer id) {
        repository.deleteById(id);
    }
}
