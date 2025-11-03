package com.transportista.solicitudes.repository;

import com.transportista.solicitudes.entity.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TramoRepository extends JpaRepository<Tramo, Long> {
    
    List<Tramo> findByRutaId(Long rutaId);
    
    List<Tramo> findByEstado(String estado);
    
    List<Tramo> findByTransportista(String transportista);
    
    @Query("SELECT t FROM Tramo t WHERE t.transportista = :transportista AND t.estado IN ('ASIGNADO', 'EN_CURSO')")
    List<Tramo> findTramosActivosByTransportista(String transportista);
}
