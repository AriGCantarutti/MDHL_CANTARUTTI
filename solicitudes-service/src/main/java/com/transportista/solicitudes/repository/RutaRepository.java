package com.transportista.solicitudes.repository;

import com.transportista.solicitudes.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    
    Optional<Ruta> findBySolicitudId(Long solicitudId);
    
    @Query("SELECT r FROM Ruta r JOIN FETCH r.tramos WHERE r.solicitud.id = :solicitudId")
    Optional<Ruta> findBySolicitudIdWithTramos(Long solicitudId);
}
