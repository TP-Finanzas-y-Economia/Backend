package com.crediapp.auth.repository;

import com.crediapp.auth.entity.RangoBono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RangoBonoRepository extends JpaRepository<RangoBono, String> {

    // Esta consulta busca el rango donde el precio del usuario esté entre el mínimo y máximo
    @Query("SELECT r FROM RangoBono r WHERE :precio >= r.precioMinimo AND :precio <= r.precioMaximo")
    Optional<RangoBono> findRangoByPrecio(@Param("precio") Double precio);
}
