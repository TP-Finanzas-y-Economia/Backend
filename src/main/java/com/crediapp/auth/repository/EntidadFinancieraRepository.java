package com.crediapp.auth.repository;

import com.crediapp.auth.entity.EntidadFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Integer> {

    @Query("SELECT e FROM EntidadFinanciera e WHERE " +
            ":sueldo >= e.ingresoMinimoRequerido AND " +
            ":precio >= e.precioMinVivienda AND " +
            ":precio <= e.precioMaxVivienda AND " +
            "(:cuotaInicial / :precio * 100) >= e.porcentajeCuotaInicialMinima")
    List<EntidadFinanciera> findBancosAptos(
            @Param("sueldo") Double sueldo,
            @Param("precio") Double precio,
            @Param("cuotaInicial") Double cuotaInicial
    );
}
