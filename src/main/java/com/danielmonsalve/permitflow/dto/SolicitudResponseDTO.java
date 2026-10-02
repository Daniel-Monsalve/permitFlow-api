package com.danielmonsalve.permitflow.dto;

import com.danielmonsalve.permitflow.entity.EstadoSolicitud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponseDTO {

    private Long id;
    private String tipoLicencia;
    private String descripcion;
    private EstadoSolicitud estado;
    private String nombreSolicitante;
    private LocalDateTime fechaRadicacion;
}
