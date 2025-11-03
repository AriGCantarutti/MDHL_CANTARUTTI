package com.transportista.solicitudes.controller;

import com.transportista.solicitudes.dto.SolicitudRequestDTO;
import com.transportista.solicitudes.dto.SolicitudResponseDTO;
import com.transportista.solicitudes.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
@Tag(name = "Solicitudes", description = "API para gestión de solicitudes de transporte")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping
    @PreAuthorize("hasAnyRole('CLIENTE', 'OPERADOR')")
    @Operation(summary = "Crear solicitud", description = "Crea una nueva solicitud de transporte")
    public ResponseEntity<SolicitudResponseDTO> crearSolicitud(@Valid @RequestBody SolicitudRequestDTO solicitudDTO) {
        SolicitudResponseDTO created = solicitudService.crearSolicitud(solicitudDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'OPERADOR', 'TRANSPORTISTA')")
    @Operation(summary = "Obtener solicitud", description = "Obtiene una solicitud por su ID")
    public ResponseEntity<SolicitudResponseDTO> obtenerSolicitud(@PathVariable Long id) {
        SolicitudResponseDTO solicitud = solicitudService.obtenerSolicitud(id);
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping("/numero/{numeroSolicitud}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'OPERADOR', 'TRANSPORTISTA')")
    @Operation(summary = "Obtener solicitud por número", description = "Obtiene una solicitud por su número")
    public ResponseEntity<SolicitudResponseDTO> obtenerSolicitudPorNumero(@PathVariable String numeroSolicitud) {
        SolicitudResponseDTO solicitud = solicitudService.obtenerSolicitudPorNumero(numeroSolicitud);
        return ResponseEntity.ok(solicitud);
    }

    @GetMapping
    @PreAuthorize("hasRole('OPERADOR')")
    @Operation(summary = "Listar solicitudes", description = "Lista todas las solicitudes")
    public ResponseEntity<List<SolicitudResponseDTO>> listarSolicitudes() {
        List<SolicitudResponseDTO> solicitudes = solicitudService.listarSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'OPERADOR')")
    @Operation(summary = "Listar solicitudes por cliente", description = "Lista las solicitudes de un cliente")
    public ResponseEntity<List<SolicitudResponseDTO>> listarSolicitudesPorCliente(@PathVariable Long clienteId) {
        List<SolicitudResponseDTO> solicitudes = solicitudService.listarSolicitudesPorCliente(clienteId);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('OPERADOR')")
    @Operation(summary = "Listar solicitudes por estado", description = "Lista las solicitudes filtradas por estado")
    public ResponseEntity<List<SolicitudResponseDTO>> listarSolicitudesPorEstado(@PathVariable String estado) {
        List<SolicitudResponseDTO> solicitudes = solicitudService.listarSolicitudesPorEstado(estado);
        return ResponseEntity.ok(solicitudes);
    }
}
