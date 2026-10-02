package com.danielmonsalve.permitflow.dto;

import com.danielmonsalve.permitflow.entity.EstadoSolicitud;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUpdateDTO {

    @NotNull(message = "El nuevo estado es obligatorio")
    private EstadoSolicitud nuevoEstado;

    private String observaciones;
}
