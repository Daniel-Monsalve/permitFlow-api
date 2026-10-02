package com.danielmonsalve.permitflow.service;

import com.danielmonsalve.permitflow.dto.EstadoUpdateDTO;
import com.danielmonsalve.permitflow.dto.SolicitudRequestDTO;
import com.danielmonsalve.permitflow.dto.SolicitudResponseDTO;

import java.util.List;

public interface SolicitudService {

    SolicitudResponseDTO crearSolicitud(SolicitudRequestDTO request);

    List<SolicitudResponseDTO> obtenerTodasLasSolicitudes();

    SolicitudResponseDTO cambiarEstadoSolicitud(Long id, EstadoUpdateDTO request);
}
