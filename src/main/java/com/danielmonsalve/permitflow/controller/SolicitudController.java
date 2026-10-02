package com.danielmonsalve.permitflow.controller;

import com.danielmonsalve.permitflow.dto.EstadoUpdateDTO;
import com.danielmonsalve.permitflow.dto.SolicitudRequestDTO;
import com.danielmonsalve.permitflow.dto.SolicitudResponseDTO;
import com.danielmonsalve.permitflow.service.SolicitudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {
    private final SolicitudService solicitudService;

    // Endpoint para CREAR una solicitud (POST)
    @PostMapping
    public ResponseEntity<SolicitudResponseDTO> crearSolicitud(@Valid @RequestBody SolicitudRequestDTO request) {
        SolicitudResponseDTO nuevaSolicitud = solicitudService.crearSolicitud(request);
        // Retornamos 201 Created en lugar del 200 OK por defecto
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    // Endpoint para OBTENER TODAS las solicitudes (GET)
    @GetMapping
    public ResponseEntity<List<SolicitudResponseDTO>> obtenerTodasLasSolicitudes() {
        List<SolicitudResponseDTO> solicitudes = solicitudService.obtenerTodasLasSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<SolicitudResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @Valid @RequestBody EstadoUpdateDTO request) {

        SolicitudResponseDTO solicitudActualizada = solicitudService.cambiarEstadoSolicitud(id, request);
        return ResponseEntity.ok(solicitudActualizada);
    }

}
