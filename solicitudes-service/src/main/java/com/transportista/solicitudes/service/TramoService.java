package com.transportista.solicitudes.service;

import com.transportista.solicitudes.dto.TramoDTO;
import com.transportista.solicitudes.entity.Tramo;
import com.transportista.solicitudes.repository.TramoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TramoService {

    @Autowired
    private TramoRepository tramoRepository;

    public List<TramoDTO> listarTodos() {
        return tramoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<TramoDTO> listarPorTransportista(String transportista) {
        return tramoRepository.findByTransportista(transportista).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<TramoDTO> listarTramosActivos(String transportista) {
        return tramoRepository.findTramosActivosByTransportista(transportista).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public TramoDTO obtenerPorId(Long id) {
        Tramo tramo = tramoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));
        return convertirADTO(tramo);
    }

    private TramoDTO convertirADTO(Tramo tramo) {
        TramoDTO dto = new TramoDTO();
        dto.setId(tramo.getId());
        dto.setRutaId(tramo.getRuta() != null ? tramo.getRuta().getId() : null);
        dto.setNumeroOrden(tramo.getNumeroOrden());
        dto.setDireccionOrigen(tramo.getDireccionOrigen());
        dto.setLatitudOrigen(tramo.getLatitudOrigen());
        dto.setLongitudOrigen(tramo.getLongitudOrigen());
        dto.setDireccionDestino(tramo.getDireccionDestino());
        dto.setLatitudDestino(tramo.getLatitudDestino());
        dto.setLongitudDestino(tramo.getLongitudDestino());
        dto.setTipo(tramo.getTipo());
        dto.setDistanciaKm(tramo.getDistanciaKm());
        dto.setCosto(tramo.getCosto());
        dto.setEstado(tramo.getEstado());
        dto.setCamionId(tramo.getCamionId());
        dto.setTransportista(tramo.getTransportista());
        dto.setDepositoOrigenId(tramo.getDepositoOrigenId());
        dto.setDepositoDestinoId(tramo.getDepositoDestinoId());
        dto.setFechaInicioReal(tramo.getFechaInicioReal());
        dto.setFechaFinReal(tramo.getFechaFinReal());
        return dto;
    }
}

