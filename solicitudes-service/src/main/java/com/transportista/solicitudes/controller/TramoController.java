package com.transportista.solicitudes.controller;

import com.transportista.solicitudes.dto.TramoDTO;
import com.transportista.solicitudes.service.TramoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tramos")
@Tag(name = "Tramos", description = "API para gestión de tramos de transporte")
public class TramoController {

    @Autowired
    private TramoService tramoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('TRANSPORTISTA', 'OPERADOR')")
    @Operation(summary = "Listar tramos", description = "Lista los tramos según el rol del usuario")
    public ResponseEntity<List<TramoDTO>> listarTramos(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        List<String> roles = jwt.getClaimAsStringList("roles");

        if (roles != null && roles.contains("TRANSPORTISTA")) {
            // Si es transportista, solo ve sus tramos
            String username = jwt.getClaimAsString("preferred_username");
            List<TramoDTO> tramos = tramoService.listarTramosActivos(username);
            return ResponseEntity.ok(tramos);
        } else {
            // Si es operador, ve todos los tramos
            List<TramoDTO> tramos = tramoService.listarTodos();
            return ResponseEntity.ok(tramos);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TRANSPORTISTA', 'OPERADOR')")
    @Operation(summary = "Obtener tramo", description = "Obtiene un tramo por su ID")
    public ResponseEntity<TramoDTO> obtenerTramo(@PathVariable Long id) {
        TramoDTO tramo = tramoService.obtenerPorId(id);
        return ResponseEntity.ok(tramo);
    }

    @GetMapping("/transportista/{username}")
    @PreAuthorize("hasRole('OPERADOR')")
    @Operation(summary = "Listar tramos por transportista", description = "Lista los tramos de un transportista específico")
    public ResponseEntity<List<TramoDTO>> listarTramosPorTransportista(@PathVariable String username) {
        List<TramoDTO> tramos = tramoService.listarPorTransportista(username);
        return ResponseEntity.ok(tramos);
    }
}

